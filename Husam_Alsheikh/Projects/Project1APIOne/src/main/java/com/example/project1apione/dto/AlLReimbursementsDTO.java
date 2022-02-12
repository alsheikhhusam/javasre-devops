package com.example.project1apione.dto;

import java.util.List;

public class AlLReimbursementsDTO {
    private List<ReimbursementDTO> reimbursementDTOList;

    public AlLReimbursementsDTO() {
    }

    public AlLReimbursementsDTO(List<ReimbursementDTO> reimbursementDTOList) {
        this.reimbursementDTOList = reimbursementDTOList;
    }

    public List<ReimbursementDTO> getReimbursementDTOList() {
        return reimbursementDTOList;
    }

    public void setReimbursementDTOList(List<ReimbursementDTO> reimbursementDTOList) {
        this.reimbursementDTOList = reimbursementDTOList;
    }
}
