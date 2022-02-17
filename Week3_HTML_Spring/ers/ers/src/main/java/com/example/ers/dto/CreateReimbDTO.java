package com.example.ers.dto;

import com.example.ers.entities.ReimbType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateReimbDTO {
    private String type;
    private Float amount;
    private String empEmail;
    private String manEmail;
}
