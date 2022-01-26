package org.example.models;

public class Greeting {
    private int id;
    private String greeting;
    private User user;

    public Greeting() {
    }

    public Greeting(int id, String greeting, User user) {
        this.id = id;
        this.greeting = greeting;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
