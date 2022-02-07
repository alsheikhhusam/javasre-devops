package com.example.mymedia.models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "mm_post")
public class MmPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "poster_id", nullable = false)
    private MmUser poster;

    @ManyToOne(optional = false)
    @JoinColumn(name = "receiver_id", nullable = false)
    private MmUser receiver;

    @Column(name = "content")
    private String content;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "mm_posts_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<MmTag> mmTags = new LinkedHashSet<>();

    public Set<MmTag> getMmTags() {
        return mmTags;
    }

    public void setMmTags(Set<MmTag> mmTags) {
        this.mmTags = mmTags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MmUser getReceiver() {
        return receiver;
    }

    public void setReceiver(MmUser receiver) {
        this.receiver = receiver;
    }

    public MmUser getPoster() {
        return poster;
    }

    public void setPoster(MmUser poster) {
        this.poster = poster;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MmPost{" +
                "id=" + id +
                ", poster=" + poster +
                ", receiver=" + receiver +
                ", content='" + content + '\'' +
                ", mmTags=" + mmTags +
                '}';
    }
}