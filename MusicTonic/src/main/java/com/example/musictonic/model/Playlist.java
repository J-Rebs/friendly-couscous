package com.example.musictonic.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "playlists")
public class Playlist {

    // definition of entity
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long PlaylistId;

    @Column(name = "owner")
    private String owner;

    @Column(name = "name")
    private String name;

    @Column(name = "isDefault")
    private Boolean isDefault;

    /**
     * Double check how OneToMany should work, route breaking for users if have this
     * think need to add mapping
     **/
    // @OneToMany
    // private List<User> users;

    // constructors
    // add default constructor for sake of JPA
    protected Playlist() {
    }

    public Playlist(Long playlistId, String owner, String name, Boolean isDefault) {
        PlaylistId = playlistId;
        this.owner = owner;
        this.name = name;
        this.isDefault = isDefault;
    }

    // getters and setters
    public Long getPlaylistId() {
        return PlaylistId;
    }

    public void setPlaylistId(Long playlistId) {
        PlaylistId = playlistId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "PlaylistId=" + PlaylistId +
                ", owner='" + owner + '\'' +
                ", name='" + name + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }

}
