package org.example.dto;

public class GreetingDTO {
    private int id;
    private String greeting;
    private UserProfileDTO username;

    public GreetingDTO() {
    }

    public GreetingDTO(int id, String greeting, UserProfileDTO username) {
        this.id = id;
        this.greeting = greeting;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public UserProfileDTO getUsername() {
        return username;
    }

    public void setUsername(UserProfileDTO username) {
        this.username = username;
    }
}
