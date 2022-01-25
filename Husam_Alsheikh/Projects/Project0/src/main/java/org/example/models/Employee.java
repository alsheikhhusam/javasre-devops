package org.example.models;

public class Employee {
    private int id;
    private String username;
    private String password;
    private Roles[] roles;

    public Employee(int id, String username, String password, Roles[] roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles[] getRoles() {
        return roles;
    }

    public void setRoles(Roles[] roles) {
        this.roles = roles;
    }
}
