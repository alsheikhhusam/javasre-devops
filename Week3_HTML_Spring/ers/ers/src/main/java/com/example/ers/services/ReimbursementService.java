package com.example.ers.services;

import com.example.ers.dto.CreateReimbDTO;
import com.example.ers.entities.ReimbStatus;
import com.example.ers.entities.ReimbType;
import com.example.ers.entities.ReimbsUser;
import com.example.ers.entities.Reimbursement;
import com.example.ers.entities.repos.ReimbursementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReimbursementService {
    // save reimb
    // get all reimbs
    // get reimb by id
    // get all by type
    // get all by status
    // get all for employee

    private ReimbursementRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(ReimbursementService.class);

    @Autowired
    public ReimbursementService(ReimbursementRepository repository) {
        this.repository = repository;
    }

    public Reimbursement saveReimbursement(CreateReimbDTO dto, ReimbsUser submitter, ReimbsUser approver) {
        Reimbursement reimb = new Reimbursement();
        reimb.setAmount(dto.getAmount());
        reimb.setType(ReimbType.valueOf(dto.getType()));
        reimb.setEmployee(submitter);
        reimb.setManager(approver);
        reimb.setStatus(ReimbStatus.PENDING);

        saveReimbursement(reimb);
        return reimb;
    }

    public void saveReimbursement(Reimbursement reimbursement) {
        repository.save(reimbursement);
    }

    public List<Reimbursement> getAllReimbursements() {
        return repository.findAll();
    }

    public Optional<Reimbursement> getById(Long id) {
        return repository.findById(id);
    }

    public List<Reimbursement> getAllByType(ReimbType type) {
        return repository.findAllByType(type);
    }

    public List<Reimbursement> getAllByStatus(ReimbStatus status) {
        return repository.findAllByStatus(status);
    }

    public List<Reimbursement> getAllForEmployee(ReimbsUser user) {
        switch (user.getRole()) {
            case ROLE_EMP:
                logger.debug("Loading reimbursements created by user {}", user.getEmail());
                return getReimbsForEmployeeUser(user);
            case ROLE_MAN:
                logger.debug("Loading reimbursements assigned to manager {}", user.getEmail());
                return getReimbsForManagerUser(user);
            default:
                return Collections.emptyList();
        }
    }

    private List<Reimbursement> getReimbsForEmployeeUser(ReimbsUser user) {
        return repository.findAllByEmployee(user);
    }
    private List<Reimbursement> getReimbsForManagerUser(ReimbsUser user) {
        return repository.findAllByManager(user);
    }
}
