package org.example.dto;

import java.util.List;

public class ListGreetingDTO {
    private List<GreetingDTO> greetings;

    public ListGreetingDTO() {

    }

    public ListGreetingDTO(List<GreetingDTO> greetings) {
        this.greetings = greetings;
    }

    public List<GreetingDTO> getGreetings() {
        return greetings;
    }

    public void setGreetings(List<GreetingDTO> greetings) {
        this.greetings = greetings;
    }
}
