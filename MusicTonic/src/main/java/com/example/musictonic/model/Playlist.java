package com.example.musictonic.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "playlists")
public class Playlist {

    // definition of entity
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long playlistId;

    @Column(name = "owner")
    private String owner;

    @Column(name = "name")
    private String name;

    @Column(name = "isDefault")
    private Boolean isDefault;

    @OneToMany(mappedBy = "playlist")
    private Set<PlaylistToSubscriber> playlistToSubscriber;

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
        playlistId = playlistId;
        this.owner = owner;
        this.name = name;
        this.isDefault = isDefault;
    }

    // getters and setters
    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        playlistId = playlistId;
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
                "PlaylistId=" + playlistId +
                ", owner='" + owner + '\'' +
                ", name='" + name + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }

}
