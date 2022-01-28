package org.example.dto;

public class AccountDTO {
    private int userid;
    private String username;
    private int balance;
    private int accountNum;

    public AccountDTO(int userid, String username, int balance, int accountNum) {
        this.userid = userid;
        this.username = username;
        this.balance = balance;
        this.accountNum = accountNum;
    }

    public AccountDTO(int userid, int accountNum, String username){
        this.userid = userid;
        this.accountNum = accountNum;
        this.username = username;
        balance = 0;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
