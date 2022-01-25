package org.example.dao;

import org.example.models.Employee;
import org.example.models.Roles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemEmpRepository implements EmpRepository{
    private AtomicInteger idGen;
    private Map<Integer, Employee> employees;

    public InMemEmpRepository() {
        idGen = new AtomicInteger(1);
        employees = new HashMap<>();

        employees.put(idGen.getAndIncrement(), new Employee(idGen.get(), "husam.alsheikh", "7841", new Roles[]{Roles.EMPLOYEE}));
    }

    @Override
    public Employee getByUsername(String username) {
        return employees.values()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().orElse(null);
    }

    @Override
    public Integer save(Employee obj) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public Employee getById(Integer integer) {
        return null;
    }

    @Override
    public void delete(Employee obj) {

    }

    @Override
    public void deleteById(Integer integer) {

    }
}
