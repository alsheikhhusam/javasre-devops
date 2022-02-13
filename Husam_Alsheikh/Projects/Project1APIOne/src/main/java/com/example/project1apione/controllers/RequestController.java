package com.example.project1apione.controllers;

import com.example.project1apione.dto.AllRequestsDTO;
import com.example.project1apione.dto.CreateRequestDTO;
import com.example.project1apione.dto.ReassignDTO;
import com.example.project1apione.models.Manager;
import com.example.project1apione.models.Request;
import com.example.project1apione.services.ManagerService;
import com.example.project1apione.services.ReimbursementService;
import com.example.project1apione.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("requests")
public class RequestController {
    @Value("3000")
    int port;

    private final RequestService requestService;
    private final ReimbursementService reimbursementService;
    private final ManagerService managerService;
    private final RestTemplate restTemplate;

    @Autowired
    public RequestController(RequestService requestService, ReimbursementService reimbursementService, ManagerService managerService, RestTemplate restTemplate) {
        this.requestService = requestService;
        this.reimbursementService = reimbursementService;
        this.managerService = managerService;
        this.restTemplate = restTemplate;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "create")
    public ResponseEntity<?> createNewRequest(@RequestBody CreateRequestDTO createRequestDTO) throws URISyntaxException {
        requestService.saveRequest(createRequestDTO);

        return ResponseEntity.created(new URI("http://localhost:" + port + "/api-one/requests/created")).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getRequests/{emp_id}")
    public ResponseEntity<AllRequestsDTO> getAllRequests(@PathVariable Integer emp_id) {
        AllRequestsDTO requests = requestService.getAllRequests(emp_id);
        return ResponseEntity.ok(requests);
    }

    @PatchMapping(consumes = MediaType.TEXT_PLAIN_VALUE, path = "approve-deny/{req_id}")
    public ResponseEntity<?> approveDeny(@PathVariable Integer req_id, @RequestBody String status){
        Request request = requestService.getRequest(req_id);
        request.setStatus(status);

        requestService.updateRequest(request);

        if(status.equals("Approve")){
            reimbursementService.saveReimbursement(request);

            //  Tell email api to send email that request has been approved
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8081/api-two/emails", status, null);

            if(responseEntity.getStatusCode().is5xxServerError()){
                return ResponseEntity.internalServerError().build();
            }
        }
        else if(status.equals("Deny")){
            //  Tell email api to send email that request has been denied
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8081/api-two/emails", status, null);

            if(responseEntity.getStatusCode().is5xxServerError()){
                return ResponseEntity.internalServerError().build();
            }

            System.out.println("Sent message: " + status);
        }
        else{
            //  Throw error
        }

        return ResponseEntity.ok(null);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "reassign/{req_id}")
    public ResponseEntity<?> reassign(@PathVariable Integer req_id, @RequestBody ReassignDTO reassignDTO){
        Request request = requestService.getRequest(req_id);
        request.setStatus(reassignDTO.getStatus());

        Manager manager = managerService.getManager(reassignDTO.getReassignTo());
        request.setManagers(manager);

        requestService.updateRequest(request);

        //  Tell email api to send email that request has been reassigned

        return ResponseEntity.ok(null);
    }
}
