package org.example.models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greeting greeting1 = (Greeting) o;
        return id == greeting1.id && greeting.equals(greeting1.greeting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, greeting);
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", greeting='" + greeting + '\'' +
                ", user=" + user +
                '}';
    }
}
