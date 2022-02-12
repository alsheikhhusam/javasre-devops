package com.example.project1apione.dto;

public class CreateRequestDTO {
    private String date;
    private double amount;
    private int emp_id;

    public CreateRequestDTO() {
    }

    public CreateRequestDTO(String date, double amount, int emp_id) {
        this.date = date;
        this.amount = amount;
        this.emp_id = emp_id;
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

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    @Override
    public String toString() {
        return "CreateRequestDTO{" +
                "date='" + date + '\'' +
                ", amount=" + amount +
                ", emp_id=" + emp_id +
                '}';
    }
}
