package com.example.project1apione.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    private int request_id;
    private Instant date;
    private double amount;
    private String status;
    private int emp_id;
    private int manager_id;
}
