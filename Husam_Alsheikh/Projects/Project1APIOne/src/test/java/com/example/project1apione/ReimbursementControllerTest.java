package com.example.project1apione;

import com.example.project1apione.controllers.ReimbursementController;
import com.example.project1apione.dto.AlLReimbursementsDTO;
import com.example.project1apione.dto.ReimbursementDTO;
import com.example.project1apione.services.ReimbursementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReimbursementController.class)
public class ReimbursementControllerTest {
    @MockBean
    private ReimbursementService reimbursementService;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getMyReimbursementsTest() throws Exception {
        int emp_id = 1;

        when(reimbursementService.getMyReimbursements(emp_id)).thenReturn(new AlLReimbursementsDTO(Collections.singletonList(new ReimbursementDTO())));

        mockMvc.perform(get("/reimbursements/getMy/{emp_id}", emp_id))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllReimbursementsTest() throws Exception {
        int manager_id = 1;

        when(reimbursementService.getAllReimbursements(manager_id)).thenReturn(new AlLReimbursementsDTO(Collections.singletonList(new ReimbursementDTO())));

        mockMvc.perform(get("/reimbursements/getAll/{manager_id}", manager_id))
                .andExpect(status().isOk());
    }
}
