package org.example.dto;

public class TransferDTO {
    private int userid;
    private String username;
    private int transferAmount;

    public TransferDTO() {
    }

    public TransferDTO(int userid, String username, int transferAmount) {
        this.userid = userid;
        this.username = username;
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

    public int getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(int transferAmount) {
        this.transferAmount = transferAmount;
    }
}
