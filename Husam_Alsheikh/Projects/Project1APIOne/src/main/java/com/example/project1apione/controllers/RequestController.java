package com.example.project1apione.controllers;

import com.example.project1apione.dto.AlLReimbursementsDTO;
import com.example.project1apione.dto.AllRequestsDTO;
import com.example.project1apione.dto.CreateRequestDTO;
import com.example.project1apione.services.ReimbursementService;
import com.example.project1apione.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("requests")
public class RequestController {
    @Value("3000")
    int port;

    private final RequestService requestService;
    private final ReimbursementService reimbursementService;

    @Autowired
    public RequestController(RequestService requestService, ReimbursementService reimbursementService) {
        this.requestService = requestService;
        this.reimbursementService = reimbursementService;
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
        if(status.equals("Approve")){

        }
        else if(status.equals("Deny")){

        }

        return ResponseEntity.ok(null);
    }
}
