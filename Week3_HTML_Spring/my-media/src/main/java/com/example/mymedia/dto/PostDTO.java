package com.example.mymedia.dto;

import java.util.Set;

public class PostDTO {
    private int id;
    private String content;
    private String posterName;
    private String receiverName;
    private Set<String> tags;

    public PostDTO() {
    }

    public PostDTO(int id, String content, String posterName, String receiverName, Set<String> tags) {
        this.id = id;
        this.content = content;
        this.posterName = posterName;
        this.receiverName = receiverName;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
