package com.example.musictonic.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * <h3>This is the analytics playlist entity.</h3>
 * Note: required for compliance with checkstyle
 */
@Entity
@Table(name = "analytics_playlist")
public class AnalyticsPlaylist implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
