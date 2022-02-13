package com.example.project1apione.dto;

public class ReassignDTO {
    private String status;
    private Integer reassignTo;

    public ReassignDTO() {
    }

    public ReassignDTO(String status, Integer reassignTo) {
        this.status = status;
        this.reassignTo = reassignTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getReassignTo() {
        return reassignTo;
    }

    public void setReassignTo(Integer reassignTo) {
        this.reassignTo = reassignTo;
    }
}
