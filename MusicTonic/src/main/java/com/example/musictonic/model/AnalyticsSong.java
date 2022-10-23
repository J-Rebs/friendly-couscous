package com.example.musictonic.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "analytics_song")
public class AnalyticsSong implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    Long id;

    @OneToOne
    @JoinColumn(name = "analyticsId")
    private Analytics analytics;

    @ManyToOne
    @JoinColumn(name = "songId")
    private Song song;

    protected AnalyticsSong() {

    }

    public AnalyticsSong(Long id, Analytics analytics, Song song) {
        this.id = id;
        this.analytics = analytics;
        this.song = song;
    }

    public AnalyticsSong(Analytics a, Song song) {
        this.analytics = a;
        this.song = song;
    }
}
