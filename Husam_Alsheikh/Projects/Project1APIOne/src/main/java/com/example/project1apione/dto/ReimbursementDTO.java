package com.example.project1apione.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReimbursementDTO {
    private int r_id;
    private Instant date;
    private Double amount;
    private int request_id;
    private int employee_id;
    private int manager_id;
}
