package com.example.ers.entities.repos;

import com.example.ers.entities.ReimbStatus;
import com.example.ers.entities.ReimbType;
import com.example.ers.entities.ReimbsUser;
import com.example.ers.entities.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement, Long> {
    List<Reimbursement> findAllByType(ReimbType type);
    List<Reimbursement> findAllByStatus(ReimbStatus status);
    List<Reimbursement> findAllByManager(ReimbsUser user);
    List<Reimbursement> findAllByEmployee(ReimbsUser user);
}