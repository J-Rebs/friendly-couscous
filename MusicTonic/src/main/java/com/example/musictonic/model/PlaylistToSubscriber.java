package com.example.musictonic.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "playlist_to_subscriber")
public class PlaylistToSubscriber implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long playlistId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    private User user;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    @Override
    public String toString() {
        return "PlaylistToSubscriber{" +
                "userId=" + userId +
                ", playlistId=" + playlistId +
                '}';
    }

}
