package com.example.project1apione.models;

import javax.persistence.*;

@Entity
@Table(name = "reimbursements")
public class Reimbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id", nullable = false)
    private Integer id;

    @Column(name = "date", length = 40)
    private String date;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Request requests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager managers;

    public Reimbursement() {
    }

    public Reimbursement(String date, Double amount, Request requests, Employee employees, Manager managers) {
        this.date = date;
        this.amount = amount;
        this.requests = requests;
        this.employees = employees;
        this.managers = managers;
    }

    public Reimbursement(Integer id, String date, Double amount, Request requests, Employee employees, Manager managers) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.requests = requests;
        this.employees = employees;
        this.managers = managers;
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

    public Request getRequests() {
        return requests;
    }

    public void setRequests(Request requests) {
        this.requests = requests;
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
}