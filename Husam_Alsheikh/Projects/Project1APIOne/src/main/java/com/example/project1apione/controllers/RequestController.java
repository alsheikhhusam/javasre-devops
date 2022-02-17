package com.example.project1apione.controllers;

import com.example.project1apione.dto.AllRequestsDTO;
import com.example.project1apione.dto.CreateRequestDTO;
import com.example.project1apione.models.Manager;
import com.example.project1apione.models.Request;
import com.example.project1apione.services.ManagerService;
import com.example.project1apione.services.ReimbursementService;
import com.example.project1apione.services.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("requests")
public class RequestController {
    @Value("3000")
    int port;

    @Value("${api.config.api2URL:http://localhost:8081/api-two/emails}")
    String url;

    private final RequestService requestService;
    private final ReimbursementService reimbursementService;
    private final ManagerService managerService;
    private final RestTemplate restTemplate;

    @Autowired
    public RequestController(RequestService requestService, ReimbursementService reimbursementService, ManagerService managerService, RestTemplate restTemplate) {
        log.info("-> Autowiring Services");
        log.info("-> Autowiring Rest Template");

        this.requestService = requestService;
        this.reimbursementService = reimbursementService;
        this.managerService = managerService;
        this.restTemplate = restTemplate;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "create")
    public ResponseEntity<?> createNewRequest(@RequestBody CreateRequestDTO createRequestDTO) throws URISyntaxException {
        requestService.saveRequest(createRequestDTO);

        log.info("-> Created new Request");

        return ResponseEntity.created(new URI("http://localhost:" + port + "/api-one/requests/created")).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getRequests/{emp_id}")
    public ResponseEntity<?> getAllRequests(@PathVariable Integer emp_id) {
        log.info("-> Getting all Requests");

        AllRequestsDTO requests = requestService.getAllRequests(emp_id);

        if(requests.getRequestDTOList().isEmpty()){
            log.error("-> No requests to retrieve");
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(requests);
    }

    @PatchMapping(consumes = MediaType.TEXT_PLAIN_VALUE, path = "approve-deny/{req_id}")
    public ResponseEntity<?> approveDeny(@PathVariable Integer req_id, @RequestBody String status) throws URISyntaxException{
        Request request = requestService.getRequest(req_id);

        //  Prevent commands other than approve or deny
        if(!status.equals("Approve") && !status.equals("Deny")){
            log.error("-> Unacceptable command");
            return ResponseEntity.ok("Add appropriate command");
        }

        //  Prevent approval/denial if already approved/denied
        if(request.getStatus().equals("Approved") || request.getStatus().equals("Denied")){
            log.error("-> Request has already been Approved/Denied");
            return ResponseEntity.ok("Request has already been Approved/Denied");
        }

        if(status.equals("Approve")){
            request.setStatus("Approved");
            requestService.updateRequest(request);

            log.info("-> Request approved");

            //  Save reimbursement
            reimbursementService.saveReimbursement(request);

            log.info("-> Reimbursement saved");
        }
        else {
            request.setStatus("Denied");
            requestService.updateRequest(request);

            log.info("-> Request Denied");
        }

        //  Tell email api to send email that request has been approved
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(url, Arrays.asList(request.getEmployees().getEmail(), request.getAmount().toString(), request.getStatus()), null);
        if(responseEntity.getStatusCode().is5xxServerError()){
            log.error("-> Email failed to send");
            return ResponseEntity.internalServerError().build();
        }

        log.info("-> Email sent to Employee");

        return ResponseEntity.ok(null);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "reassign/{req_id}")
    public ResponseEntity<?> reassign(@PathVariable Integer req_id, @RequestBody String reassignTo){
        Request request = requestService.getRequest(req_id);

        //  If already Approved/Denied/Reassigned
        if(!request.getStatus().equals("Pending")){
            log.error("-> Request already Approved/Denied/Reassigned");
            return ResponseEntity.ok("Request already A/D/R, cannot reassign");
        }

        request.setStatus("Reassigned");

        Manager manager = managerService.getManager(Integer.parseInt(reassignTo));
        request.setManagers(manager);

        requestService.updateRequest(request);

        log.info("-> Request reassigned to Manager {}", manager.getId());

        //  Tell email api to send email that request has been reassigned
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8081/api-two/emails", request.getStatus(), null);
        if(responseEntity.getStatusCode().is5xxServerError()){
            log.error("-> Email failed to send");
            return ResponseEntity.internalServerError().build();
        }

        log.info("-> Email sent to Employee");

        return ResponseEntity.ok(null);
    }
}
