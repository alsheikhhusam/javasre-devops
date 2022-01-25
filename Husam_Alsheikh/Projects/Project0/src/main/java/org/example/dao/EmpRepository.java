package org.example.dao;

import org.example.models.Employee;

public interface EmpRepository extends Repository<Integer, Employee> {
    Employee getByUsername(String username);
}
