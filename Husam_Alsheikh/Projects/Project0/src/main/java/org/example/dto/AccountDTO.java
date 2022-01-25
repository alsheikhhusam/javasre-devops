package org.example.dto;

public class AccountDTO {
    private int accountNum;
    private String name;
    private int balance;

    public AccountDTO(int accountNum, String name, int balance) {
        this.accountNum = accountNum;
        this.name = name;
        this.balance = balance;
    }

    public AccountDTO(String name, int accountNum){
        this.name = name;
        this.accountNum = accountNum;
        balance = 0;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
