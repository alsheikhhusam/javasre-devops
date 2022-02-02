package com.example.mymedia.dto;

import java.util.List;

public class AllPostsDTO {
    private List<PostDTO> posts;

    public AllPostsDTO() {
    }

    public AllPostsDTO(List<PostDTO> posts) {
        this.posts = posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }
}
