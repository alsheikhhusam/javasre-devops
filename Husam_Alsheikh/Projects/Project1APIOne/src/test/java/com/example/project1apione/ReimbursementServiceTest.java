package com.example.project1apione;

import com.example.project1apione.dao.ReimbursementRepository;
import com.example.project1apione.dto.AlLReimbursementsDTO;
import com.example.project1apione.services.ReimbursementService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReimbursementServiceTest {
    private ReimbursementRepository reimbursementRepository;
    private ReimbursementService reimbursementService;

    @BeforeEach
    public void initBeforeEach(){
        reimbursementRepository = mock(ReimbursementRepository.class);
        reimbursementService = new ReimbursementService(reimbursementRepository);
    }

    @Test
    public void getMyReimTest(){
        int emp_id = 1;

        when(reimbursementRepository.getAllByEmployees_Id(emp_id)).thenReturn(Collections.emptyList());

        AlLReimbursementsDTO reimbursements = reimbursementService.getMyReimbursements(emp_id);

        Assert.assertTrue(reimbursements.getReimbursementDTOList().size() == 0);
    }

    @Test
    public void getAllReimTest(){
        int manager_id = 1;

        when(reimbursementRepository.getAllByManagers_Id(manager_id)).thenReturn(Collections.emptyList());

        AlLReimbursementsDTO reimbursements = reimbursementService.getMyReimbursements(manager_id);

        Assert.assertTrue(reimbursements.getReimbursementDTOList().size() == 0);
    }
}
