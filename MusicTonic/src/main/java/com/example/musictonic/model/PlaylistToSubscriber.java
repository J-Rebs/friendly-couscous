package com.example.musictonic.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * <h3>This is the playlist to playlist to subscriber entity.</h3>
 * Note: required for compliance with checkstyle
 */
@Entity
@Table(name = "playlist_to_subscriber")
public class PlaylistToSubscriber implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne
  @JoinColumn(name = "userId", nullable = true)
  private User user;

  @ManyToOne
  @JoinColumn(name = "playlistId", nullable = true)
  private Playlist playlist;

  protected PlaylistToSubscriber() {

  }

  /**
   * Parameterized constructor for PlaylistToSubscriber.
   *
   * @param id - the unique ID for this PlaylistToSubscriber entry
   * @param user - the user ID for this PlaylistToSubscriber entry
   * @param playlist -  the playlist ID for this PlaylistToSubscriber entry
   */
  public PlaylistToSubscriber(Long id, User user, Playlist playlist) {
    this.id = id;
    this.user = user;
    this.playlist = playlist;
  }
}
