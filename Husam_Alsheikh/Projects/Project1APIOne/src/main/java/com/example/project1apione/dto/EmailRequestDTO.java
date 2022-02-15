package com.example.project1apione.dto;

import com.example.project1apione.models.Request;

public class EmailRequestDTO {
    private String email;
    private String status;
    private double amount;

    public EmailRequestDTO() {
    }

    public EmailRequestDTO(Request request){
        this.email = request.getEmployees().getEmail();
        this.status = request.getStatus();
        this.amount = request.getAmount();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
