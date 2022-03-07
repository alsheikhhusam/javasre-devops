package com.example.project1apione;


import com.example.project1apione.dao.EmployeeRepository;
import com.example.project1apione.dao.ManagerRepository;
import com.example.project1apione.dao.RequestRepository;
import com.example.project1apione.dto.AllRequestsDTO;
import com.example.project1apione.models.Request;
import com.example.project1apione.services.RequestService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestServiceTest {
    private RequestRepository requestRepository;
    private EmployeeRepository employeeRepository;
    private ManagerRepository managerRepository;
    private RequestService requestService;

    @BeforeEach
    public void initBeforeEach(){
        requestRepository = mock(RequestRepository.class);
        employeeRepository = mock(EmployeeRepository.class);
        managerRepository = mock(ManagerRepository.class);
        requestService = new RequestService(requestRepository, employeeRepository, managerRepository);
    }

    @Test
    public void getAllRequestsTest(){
        int emp_id = 1;

        when(requestRepository.getAllByEmployees_Id(emp_id)).thenReturn(Collections.emptyList());

        AllRequestsDTO requests = requestService.getAllRequests(emp_id);

        Assert.assertTrue(requests.getRequestDTOList().size() == 0);
    }

    @Test
    public void getRequestTest(){
        int req_id = 1;

        Request tempR = new Request();
        when(requestRepository.getById(req_id)).thenReturn(tempR);

        Request request = requestService.getRequest(req_id);

        Assertions.assertTrue(request.equals(tempR));
    }
}
