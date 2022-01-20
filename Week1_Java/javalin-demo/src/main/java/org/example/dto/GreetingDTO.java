package org.example.dto;

public class GreetingDTO {
    private int id;
    private String greeting;

    public GreetingDTO() {
    }

    public GreetingDTO(int id, String greeting) {
        this.id = id;
        this.greeting = greeting;
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
}
