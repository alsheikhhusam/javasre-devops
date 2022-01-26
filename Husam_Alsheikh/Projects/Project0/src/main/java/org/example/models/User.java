package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private List<Integer> accountsId;
    private Roles[] roles;

    public User(){
        accountsId = new ArrayList<>();
    }

    public User(int id, String username, String password, List<Integer> accountsId, Roles[] roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountsId = accountsId;
        this.roles = roles;
    }

    public User(int id, String username, String password, Roles[] roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;

        accountsId = new ArrayList<>();
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

    public List<Integer> getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(List<Integer> accountsId) {
        this.accountsId = accountsId;
    }
}
