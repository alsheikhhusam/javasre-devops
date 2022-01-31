package org.example.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionDTO {
    private String date;
    private int userid;
    private String username;
    private int accountNum;
    private int amount;
    private String description;

    public TransactionDTO() {
    }

    public TransactionDTO(int userid, String username, int accountNum, String description) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");
        Date dateTime = new Date();
        date = dateFormat.format(dateTime);

        this.userid = userid;
        this.username = username;
        this.accountNum = accountNum;
        this.description = description;
    }

    public TransactionDTO(int userid, String username, int accountNum, int amount, String description) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");
        Date dateTime = new Date();
        date = dateFormat.format(dateTime);

        this.userid = userid;
        this.username = username;
        this.accountNum = accountNum;
        this.amount = amount;
        this.description = description;
    }

    public TransactionDTO(String date, int userid, String username, int accountNum, int amount, String description) {
        this.date = date;
        this.userid = userid;
        this.username = username;
        this.accountNum = accountNum;
        this.amount = amount;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
