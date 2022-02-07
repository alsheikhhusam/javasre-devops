package com.example.mymedia.models;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "mm_tag", indexes = {
        @Index(name = "mm_tag_name_key", columnList = "name", unique = true)
})
public class MmTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "mmTags", fetch = FetchType.LAZY)
    private Set<MmPost> mmPosts = new LinkedHashSet<>();

    public MmTag() {
    }

    @Formula("(select count(*) from mm_likes likes where likes.post_id = id)")
    private long likes;

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public MmTag(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Set<MmPost> getMmPosts() {
        return mmPosts;
    }

    public void setMmPosts(Set<MmPost> mmPosts) {
        this.mmPosts = mmPosts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}