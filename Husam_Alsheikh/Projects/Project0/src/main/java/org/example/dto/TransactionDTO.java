package org.example.dto;

import java.util.Date;

public class TransactionDTO {
    private Date date;
    private int userid;
    private String username;
    private int accountNum;
    private int amount;

    public TransactionDTO() {
    }

    public TransactionDTO(Date date, int userid, String username, int accountNum, int amount) {
        this.date = date;
        this.userid = userid;
        this.username = username;
        this.accountNum = accountNum;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
