package com.example.mymedia.models;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Table(name = "mm_post")
@Entity
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "mm_posts_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<MmTag> tags = new LinkedHashSet<>();


    @Formula("(select count(*) from mm_likes likes where likes.post_id = id)")
    private long likes;


    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public Set<MmTag> getTags() {
        return tags;
    }

    public void setTags(Set<MmTag> mmTags) {
        this.tags = mmTags;
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
                ", tags=" + tags +
                ", likes=" + likes +
                '}';
    }
}