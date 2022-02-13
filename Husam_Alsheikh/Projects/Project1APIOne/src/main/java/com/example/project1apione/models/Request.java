package com.example.project1apione.models;

import com.example.project1apione.dto.CreateRequestDTO;

import javax.persistence.*;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", nullable = false)
    private Integer id;

    @Column(name = "date", length = 40)
    private String date;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status", length = 15)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager managers;

    public Request() {
    }

    public Request(Integer id, String date, Double amount, String status, Employee employees, Manager managers) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.status = status;
        this.employees = employees;
        this.managers = managers;
    }

    public Request(CreateRequestDTO requestDTO, Employee employee, Manager manager) {
        this.date = requestDTO.getDate();
        this.amount = requestDTO.getAmount();
        this.employees = employee;
        this.managers = manager;
        this.status = "Pending";
    }

    public Manager getManagers() {
        return managers;
    }

    public void setManagers(Manager managers) {
        this.managers = managers;
    }

    public Employee getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employees) {
        this.employees = employees;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", employees=" + employees +
                ", managers=" + managers +
                '}';
    }
}