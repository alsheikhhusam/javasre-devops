package com.example.project1apione.dto;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequestDTO {
    private double amount;
    private int emp_id;
}
