package com.example.project1apione.dto;

public class ReimbursementDTO {
    private int r_id;
    private String date;
    private Double amount;
    private int request_id;
    private int employee_id;
    private int manager_id;

    public ReimbursementDTO() {
    }

    public ReimbursementDTO(int r_id, String date, Double amount, int request_id, int employee_id, int manager_id) {
        this.r_id = r_id;
        this.date = date;
        this.amount = amount;
        this.request_id = request_id;
        this.employee_id = employee_id;
        this.manager_id = manager_id;
    }

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }
}
