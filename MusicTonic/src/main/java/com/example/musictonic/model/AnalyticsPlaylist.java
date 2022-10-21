package com.example.musictonic.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "analytics_playlist")
public class AnalyticsPlaylist implements Serializable {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "analyticsId")
    private Analytics analytics;

    @ManyToOne
    @JoinColumn(name = "playlistId")
    private Playlist playlist;

    protected AnalyticsPlaylist() {

    }

    public AnalyticsPlaylist(Long id, Analytics analytics, Playlist playlist) {
        this.id = id;
        this.analytics = analytics;
        this.playlist = playlist;
    }

}
