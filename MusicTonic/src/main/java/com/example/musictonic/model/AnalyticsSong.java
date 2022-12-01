package com.example.musictonic.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * <h3>This is the analytics song entity.</h3>
 * Note: required for compliance with checkstyle
 */
@Entity
@Table(name = "analytics_song")
public class AnalyticsSong implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @OneToOne
  @JoinColumn(name = "analyticsId")
  private Analytics analytics;

  @ManyToOne
  @JoinColumn(name = "songId")
  private Song song;

  protected AnalyticsSong() {

  }

  /**
   * Parameterized constructor for the class AnalyticsSong.
   *
   * @param id        - the unique ID for this AnalyticsSong entry
   * @param analytics - the corresponding entity for Analytics entry
   * @param song      - the corresponding entity for Song
   */
  public AnalyticsSong(Long id, Analytics analytics, Song song) {
    this.id = id;
    this.analytics = analytics;
    this.song = song;
  }

  public AnalyticsSong(Analytics a, Song song) {
    this.analytics = a;
    this.song = song;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Analytics getAnalytics() {
    return analytics;
  }

  public void setAnalytics(Analytics analytics) {
    this.analytics = analytics;
  }

  public Song getSong() {
    return song;
  }

  public void setSong(Song song) {
    this.song = song;
  }
}
