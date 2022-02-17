package com.example.project1apione.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlLReimbursementsDTO {
    private List<ReimbursementDTO> reimbursementDTOList;
}
