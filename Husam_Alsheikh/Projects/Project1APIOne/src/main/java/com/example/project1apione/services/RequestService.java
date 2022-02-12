package com.example.project1apione.services;

import com.example.project1apione.dao.EmployeeRepository;
import com.example.project1apione.dao.ManagerRepository;
import com.example.project1apione.dao.RequestRepository;
import com.example.project1apione.dto.AllRequestsDTO;
import com.example.project1apione.dto.CreateRequestDTO;
import com.example.project1apione.dto.RequestDTO;
import com.example.project1apione.models.Employee;
import com.example.project1apione.models.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository, EmployeeRepository employeeRepository, ManagerRepository managerRepository) {
        this.requestRepository = requestRepository;
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
    }

    public AllRequestsDTO getAllRequests(Integer emp_id) {
        List<Request> allRequests = requestRepository.getAllByEmployees_Id(emp_id);

        List<RequestDTO> transformed =  allRequests.stream()
                .map(r -> new RequestDTO(r.getId(), r.getDate(), r.getAmount(), r.getStatus(), r.getEmployees().getId(), r.getManagers().getId()))
                .collect(Collectors.toList());

        return new AllRequestsDTO(transformed);
    }

    public void saveRequest(CreateRequestDTO requestDTO){
        Employee employee = employeeRepository.getById(requestDTO.getEmp_id());

        requestRepository.save(new Request(requestDTO, employee, managerRepository.getById(employee.getManagers().getId())));
    }

    public void setRequestStatus(String status){

    }
}
