package org.example.dto;

public class CreateGreetingDTO {
    private String greetingText;

    public CreateGreetingDTO() {

    }

    public CreateGreetingDTO(String greetingText) {
        this.greetingText = greetingText;
    }

    public String getGreetingText() {
        return greetingText;
    }

    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }
}
