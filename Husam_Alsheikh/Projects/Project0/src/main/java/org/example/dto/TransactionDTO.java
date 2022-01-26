package org.example.dto;

import java.util.Date;

public class TransactionDTO {
    private Date date;
    private String username;
    private int userid;
    private int accountNum;
    private int depositedAmount;
    private int withdrawnAmount;
    private int transferredAmount;

    public TransactionDTO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getDepositedAmount() {
        return depositedAmount;
    }

    public void setDepositedAmount(int depositedAmount) {
        this.depositedAmount = depositedAmount;
    }

    public int getWithdrawnAmount() {
        return withdrawnAmount;
    }

    public void setWithdrawnAmount(int withdrawnAmount) {
        this.withdrawnAmount = withdrawnAmount;
    }

    public int getTransferredAmount() {
        return transferredAmount;
    }

    public void setTransferredAmount(int transferredAmount) {
        this.transferredAmount = transferredAmount;
    }
}
