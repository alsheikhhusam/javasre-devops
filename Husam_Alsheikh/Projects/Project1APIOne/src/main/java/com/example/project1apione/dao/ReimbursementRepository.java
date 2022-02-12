package com.example.project1apione.dao;

import com.example.project1apione.models.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement, Integer> {
    public List<Reimbursement> getAllByEmployees_Id(Integer emp_id);

    public List<Reimbursement> getAllByManagers_Id(Integer manager_id);
}
