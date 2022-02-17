package com.example.project1apione.controllers;

import com.example.project1apione.dto.AlLReimbursementsDTO;
import com.example.project1apione.services.ReimbursementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("reimbursements")
public class ReimbursementController {
    @Value("3000")
    int port;

    private final ReimbursementService reimbursementService;

    /**
     * @param reimbursementService Autowire ReimbursementService
     */
    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService) {
        log.info("-> Autowiring services");
        this.reimbursementService = reimbursementService;
    }

    /**
     * @param emp_id Employee Id to find reimbursements belonging to that employee
     * @return Returns list of reimbursements belonging to employee
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getMy/{emp_id}")
    public ResponseEntity<?> getMyReimbursements(@PathVariable Integer emp_id) {
        AlLReimbursementsDTO reimbursements = reimbursementService.getMyReimbursements(emp_id);

        if(reimbursements.getReimbursementDTOList().isEmpty()){
            log.error("-> No Reimbursements to be retrieved");
            return ResponseEntity.status(404).build();
        }

        log.info("-> Reimbursements retrieved");

        return ResponseEntity.ok(reimbursements);
    }

    /**
     * @param manager_id Manager Id to find reimbursements that manager is responsible for
     * @return Returns list of reimbursements that manager is responsible for
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getAll/{manager_id}")
    public ResponseEntity<?> getAllReimbursements(@PathVariable Integer manager_id){
        AlLReimbursementsDTO reimbursements = reimbursementService.getAllReimbursements(manager_id);

        if(reimbursements.getReimbursementDTOList().isEmpty()){
            log.error("-> No Reimbursements to be retrieved");
            return ResponseEntity.status(404).build();
        }

        log.info("-> All reimbursements retrieved");

        return ResponseEntity.ok(reimbursements);
    }
}
