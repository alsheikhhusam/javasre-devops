package org.example.models;

import org.example.dto.TransactionDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class User {
    private int id;
    private String username;
    private String password;
    private List<Integer> accountsId;
    private Set<Roles> roles;
    private List<TransactionDTO> transactions;

    public User(){
        accountsId = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public User(int id, String username, String password, List<Integer> accountsId, Set<Roles> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountsId = accountsId;
        this.roles = roles;
    }

    public User(int id, String username, String password, Set<Roles> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;

        accountsId = new ArrayList<>();
        transactions = new ArrayList<>();
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

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public List<Integer> getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(List<Integer> accountsId) {
        this.accountsId = accountsId;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(TransactionDTO transactionDTO){
        transactions.add(transactionDTO);
    }
}
