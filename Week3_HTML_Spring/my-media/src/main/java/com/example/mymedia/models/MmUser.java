package com.example.mymedia.models;

import javax.persistence.*;

@Entity
@Table(name = "mm_user")
public class MmUser {

    //primary key mapping
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "hash", nullable = false)
    private String hash;

    @OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", nullable = false)
    private MmProfile profile;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private MmRole role;

    public MmUser() {
    }

    public MmUser(Integer id, String username, String hash, MmProfile profile, MmRole role) {
        this.id = id;
        this.username = username;
        this.hash = hash;
        this.profile = profile;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public MmProfile getProfile() {
        return profile;
    }

    public void setProfile(MmProfile profile) {
        this.profile = profile;
    }

    public MmRole getRole() {
        return role;
    }

    public void setRole(MmRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "MmUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", hash='" + hash + '\'' +
                ", profile=" + profile +
                ", role=" + role +
                '}';
    }
}
