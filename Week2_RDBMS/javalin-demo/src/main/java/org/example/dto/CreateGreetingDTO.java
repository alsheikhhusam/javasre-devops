package org.example.dto;

import org.example.models.User;

public class CreateGreetingDTO {
    private String greetingText;
    private User user;

    public CreateGreetingDTO() {
    }

    public CreateGreetingDTO(String greetingText, User user) {
        this.greetingText = greetingText;
        this.user = user;
    }

    public String getGreetingText() {
        return greetingText;
    }

    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
