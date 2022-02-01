package com.example.mymedia.models;

public class MmPost {
    private Integer id;

    private String posterName;
    private String receiverName;

    private String content;

    public MmPost() {
    }

    public MmPost(Integer id, String posterName, String receiverName, String content) {
        this.id = id;
        this.posterName = posterName;
        this.receiverName = receiverName;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
