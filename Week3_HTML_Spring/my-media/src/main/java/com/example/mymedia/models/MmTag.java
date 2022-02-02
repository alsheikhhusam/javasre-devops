package com.example.mymedia.models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Table(name = "mm_tag", indexes = {
        @Index(name = "mm_tag_name_key", columnList = "name", unique = true)
})
@Entity
public class MmTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public MmTag() {
    }

    public MmTag(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<MmPost> mmPosts = new LinkedHashSet<>();

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