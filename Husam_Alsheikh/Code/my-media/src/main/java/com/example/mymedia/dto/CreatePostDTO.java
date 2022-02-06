package com.example.mymedia.dto;

public class CreatePostDTO {
    private String content;
    private String posterName;
    private String receiverName;

    public CreatePostDTO() {
    }

    public CreatePostDTO(String content, String posterName, String receiverName) {
        this.content = content;
        this.posterName = posterName;
        this.receiverName = receiverName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
