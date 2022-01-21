package org.example.dto;

import java.util.List;

public class ListGreetingDTO {
    private List<GreetingDTO> greetings;

    public ListGreetingDTO() {
    }

    public List<GreetingDTO> getGreetings() {
        return greetings;
    }

    public ListGreetingDTO(List<GreetingDTO> greetings) {
        this.greetings = greetings;
    }

    public void setGreetings(List<GreetingDTO> greetings) {
        this.greetings = greetings;
    }
}
