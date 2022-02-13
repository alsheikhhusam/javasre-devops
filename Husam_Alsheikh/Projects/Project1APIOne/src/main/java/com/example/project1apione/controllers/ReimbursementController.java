package com.example.project1apione.controllers;

import com.example.project1apione.dto.AlLReimbursementsDTO;
import com.example.project1apione.services.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reimbursements")
public class ReimbursementController {
    @Value("3000")
    int port;

    private ReimbursementService reimbursementService;

    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getMy/{emp_id}")
    public ResponseEntity<AlLReimbursementsDTO> getMyReimbursements(@PathVariable Integer emp_id) {
        AlLReimbursementsDTO reimbursements = reimbursementService.getMyReimbursements(emp_id);
        return ResponseEntity.ok(reimbursements);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getAll/{manager_id}")
    public ResponseEntity<AlLReimbursementsDTO> getAllReimbursements(@PathVariable Integer manager_id){
        AlLReimbursementsDTO reimbursements = reimbursementService.getAllReimbursements(manager_id);
        
        return ResponseEntity.ok(reimbursements);
    }
}
