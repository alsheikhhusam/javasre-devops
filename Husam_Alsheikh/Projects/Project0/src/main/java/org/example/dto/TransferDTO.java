package org.example.dto;

public class TransferDTO {
    private int userid;
    private String username;
    private String password;
    private int accountNum;
    private int transferAmount;

    public TransferDTO() {
    }

    public TransferDTO(int userid, String username, String password, int accountNum, int transferAmount) {
        this.userid = userid;
        this.username = username;
        this.accountNum = accountNum;
        this.transferAmount = transferAmount;
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

    public int getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(int transferAmount) {
        this.transferAmount = transferAmount;
    }
}
