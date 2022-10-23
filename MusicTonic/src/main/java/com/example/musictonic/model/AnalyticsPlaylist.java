package com.example.musictonic.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "analytics_playlist")
public class AnalyticsPlaylist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    Long id;

    @OneToOne
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

    public AnalyticsPlaylist(Analytics a, Playlist playlist) {
        this.analytics = a;
        this.playlist = playlist;
    }
}
