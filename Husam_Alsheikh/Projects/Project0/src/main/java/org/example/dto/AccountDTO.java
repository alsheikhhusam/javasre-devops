package org.example.dto;

public class AccountDTO {
    private int userid;
    private int accountNum;
    private int balance;
    private String username;

    public AccountDTO(int userid, int accountNum, int balance, String name) {
        this.userid = userid;
        this.accountNum = accountNum;
        this.balance = balance;
        this.username = name;
    }

    public AccountDTO(int userid,int accountNum, String username){
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
