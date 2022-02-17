package com.example.ers.controllers;

import com.example.ers.dto.CreateReimbDTO;
import com.example.ers.dto.UpdateStatusDTO;
import com.example.ers.entities.ReimbStatus;
import com.example.ers.entities.ReimbsUser;
import com.example.ers.entities.Reimbursement;
import com.example.ers.services.ReimbsUserService;
import com.example.ers.services.ReimbursementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("reimbs")
public class ReimbursementController {
    // post reimbursement
    // get all
    // get mine
    // get by id
    // put reimb

    private ReimbursementService reimbursementService;
    private ReimbsUserService userService;
    private static final Logger logger = LoggerFactory.getLogger(ReimbursementController.class);


    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService, ReimbsUserService userService) {
        this.reimbursementService = reimbursementService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        logger.debug("Loading all reimbursements");
        List<Reimbursement> allReimbs = reimbursementService.getAllReimbursements();

        if(allReimbs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(allReimbs);
    }

    @GetMapping("{id}/reimbs")
    public ResponseEntity getMine(@PathVariable("id") Long userId) {
        logger.debug("Loading user with id {}", userId);
        Optional<ReimbsUser> optUser = userService.getUserById(userId);

        if(!optUser.isPresent()) {
            logger.debug("User not found");
            return ResponseEntity.badRequest().body("User could not be found");
        }

        ReimbsUser user = optUser.get();
        logger.debug("Found user {}", user.getEmail());

        List<Reimbursement> userReimbs = reimbursementService.getAllForEmployee(user);

        if(userReimbs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(userReimbs);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postReimb(@RequestBody CreateReimbDTO reimb) {
        try {
            Optional<ReimbsUser> optSubmitter = userService.getUserByEmail(reimb.getEmpEmail());
            Optional<ReimbsUser> optApprover = userService.getUserByEmail(reimb.getManEmail());

            if(!optSubmitter.isPresent() || !optApprover.isPresent()) {
                return ResponseEntity.badRequest().body("Both users are required");
            }

            Reimbursement newReimb = reimbursementService.saveReimbursement(reimb, optSubmitter.get(), optApprover.get());
            return ResponseEntity.created(new URI("http://localhost:8080/reimbs/"+newReimb.getId())).build();

        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PatchMapping("{id}/update/status")
    public ResponseEntity updateStatus(@PathVariable("id") Long reimbId, @RequestBody UpdateStatusDTO newStatus) {
        try {
            Optional<Reimbursement> optReimb = reimbursementService.getById(reimbId);

            if(!optReimb.isPresent()) {
                return ResponseEntity.badRequest().body("Reimburse does not exist");
            }

            Reimbursement reimb = optReimb.get();
            reimb.setStatus(ReimbStatus.valueOf(newStatus.getNewStatus()));
            reimbursementService.saveReimbursement(reimb);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
