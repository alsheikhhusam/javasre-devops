package com.example.project1apione;

import com.example.project1apione.controllers.RequestController;
import com.example.project1apione.dto.AllRequestsDTO;
import com.example.project1apione.dto.RequestDTO;
import com.example.project1apione.services.ManagerService;
import com.example.project1apione.services.ReimbursementService;
import com.example.project1apione.services.RequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RequestController.class)
public class RequestControllerTest {
    @MockBean
    private RequestService requestService;

    @MockBean
    private ReimbursementService reimbursementService;

    @MockBean
    private ManagerService managerService;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllRequestsTest() throws Exception {
        int emp_id = 1;

        when(requestService.getAllRequests(emp_id)).thenReturn(new AllRequestsDTO(Collections.singletonList(new RequestDTO())));

        mockMvc.perform(get("/requests/getRequests/{emp_id}", emp_id))
                .andExpect(status().isOk());
    }

    @Test
    public void createNewRequestTest() throws Exception {
        requestService.saveRequest(any());

        mockMvc.perform(post("/requests/create")
                .contentType("application/json")
                .content("{\"amount\": 200.00, \"emp_id\": 1}"))
                .andExpect(status().isCreated());
    }
}
