package org.example.dto;

public class CreateAccountDTO {
    private int userid;
    private String username;

    public CreateAccountDTO() {}

    public CreateAccountDTO(int userId, String username) {
        this.userid = userId;
        this.username = username;
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
}
