package com.example.project1apione.dto;

public class RequestDTO {
    private int request_id;
    private String date;
    private double amount;
    private String status;
    private int emp_id;
    private int manager_id;

    public RequestDTO() {
    }

    public RequestDTO(int request_id, String date, double amount, String status, int emp_id, int manager_id) {
        this.request_id = request_id;
        this.date = date;
        this.amount = amount;
        this.status = status;
        this.emp_id = emp_id;
        this.manager_id = manager_id;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }
}
