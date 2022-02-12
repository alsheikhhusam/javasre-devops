package com.example.project1apione.services;

import com.example.project1apione.dao.EmployeeRepository;
import com.example.project1apione.dao.ManagerRepository;
import com.example.project1apione.dao.ReimbursementRepository;
import com.example.project1apione.dto.AlLReimbursementsDTO;
import com.example.project1apione.dto.ReimbursementDTO;
import com.example.project1apione.models.Reimbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReimbursementService {
    private final ReimbursementRepository reimbursementRepository;
    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    public ReimbursementService(ReimbursementRepository reimbursementRepository, EmployeeRepository employeeRepository, ManagerRepository managerRepository) {
        this.reimbursementRepository = reimbursementRepository;
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
    }

    public AlLReimbursementsDTO getMyReimbursements(Integer emp_id) {
        List<Reimbursement> allReimbursements = reimbursementRepository.getAllByEmployees_Id(emp_id);

        List<ReimbursementDTO> transformed =  allReimbursements.stream()
                .map(r -> new ReimbursementDTO(r.getId(), r.getDate(), r.getAmount(), r.getRequests().getId(), r.getEmployees().getId(), r.getManagers().getId()))
                .collect(Collectors.toList());

        return new AlLReimbursementsDTO(transformed);
    }

    public AlLReimbursementsDTO getAllReimbursements(Integer manager_id) {
        List<Reimbursement> allReimbursements = reimbursementRepository.getAllByManagers_Id(manager_id);

        List<ReimbursementDTO> transformed =  allReimbursements.stream()
                .map(r -> new ReimbursementDTO(r.getId(), r.getDate(), r.getAmount(), r.getRequests().getId(), r.getEmployees().getId(), r.getManagers().getId()))
                .collect(Collectors.toList());

        return new AlLReimbursementsDTO(transformed);
    }
}
