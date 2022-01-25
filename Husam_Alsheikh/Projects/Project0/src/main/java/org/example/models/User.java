package org.example.models;

public class User {
    private int id;
    private String username;
    private String password;
    private int accountNum;
    private Roles[] roles;

    public User(int id, String username, String password, int accountNum, Roles[] roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountNum = accountNum;
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

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public Roles[] getRoles() {
        return roles;
    }

    public void setRoles(Roles[] roles) {
        this.roles = roles;
    }
}
