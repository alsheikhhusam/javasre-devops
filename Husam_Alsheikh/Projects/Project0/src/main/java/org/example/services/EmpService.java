package org.example.services;

import org.example.dao.EmpRepository;
import org.example.models.Employee;

public class EmpService {
    private EmpRepository empRepository;

    public EmpService(EmpRepository empRepository) {
        this.empRepository = empRepository;
    }
    public Employee getEmpByUsername(String username) {
        return empRepository.getByUsername(username);
    }

}
