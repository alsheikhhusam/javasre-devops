package com.example.project1apione.dto;

import java.util.List;

public class AllRequestsDTO {
    private List<RequestDTO> requestDTOList;

    public AllRequestsDTO() {
    }

    public AllRequestsDTO(List<RequestDTO> requestDTOList) {
        this.requestDTOList = requestDTOList;
    }

    public List<RequestDTO> getRequestDTOList() {
        return requestDTOList;
    }

    public void setRequestDTOList(List<RequestDTO> requestDTOList) {
        this.requestDTOList = requestDTOList;
    }
}
