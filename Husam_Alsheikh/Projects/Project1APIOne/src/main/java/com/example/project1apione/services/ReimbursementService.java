package com.example.project1apione.services;

import com.example.project1apione.dao.ReimbursementRepository;
import com.example.project1apione.dto.AlLReimbursementsDTO;
import com.example.project1apione.dto.ReimbursementDTO;
import com.example.project1apione.models.Reimbursement;
import com.example.project1apione.models.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReimbursementService {
    private final ReimbursementRepository reimbursementRepository;

    /**
     * @param reimbursementRepository Autowire Reimbursement repo
     */
    @Autowired
    public ReimbursementService(ReimbursementRepository reimbursementRepository) {
        this.reimbursementRepository = reimbursementRepository;
    }

    /**
     * @param emp_id Employee Id
     * @return Returns list of reimbursements associated with the employee
     */
    public AlLReimbursementsDTO getMyReimbursements(Integer emp_id) {
        List<Reimbursement> allReimbursements = reimbursementRepository.getAllByEmployees_Id(emp_id);

        List<ReimbursementDTO> transformed =  allReimbursements.stream()
                .map(r -> new ReimbursementDTO(r.getId(), r.getDate(), r.getAmount(), r.getRequests().getId(), r.getEmployees().getId(), r.getManagers().getId()))
                .collect(Collectors.toList());

        return new AlLReimbursementsDTO(transformed);
    }

    /**
     * @param manager_id Manager Id
     * @return Returns list of reimbursements associated with the manager
     */
    public AlLReimbursementsDTO getAllReimbursements(Integer manager_id) {
        List<Reimbursement> allReimbursements = reimbursementRepository.getAllByManagers_Id(manager_id);

        List<ReimbursementDTO> transformed =  allReimbursements.stream()
                .map(r -> new ReimbursementDTO(r.getId(), r.getDate(), r.getAmount(), r.getRequests().getId(), r.getEmployees().getId(), r.getManagers().getId()))
                .collect(Collectors.toList());

        return new AlLReimbursementsDTO(transformed);
    }

    /**
     * @param request Request to be saved
     */
    public void saveReimbursement(Request request){
        reimbursementRepository.save(new Reimbursement(request.getAmount(), request, request.getEmployees(), request.getManagers()));
    }
}
