package com.example.musictonic.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * <h3>This is the playlist entity.</h3>
 * Note: required for compliance with checkstyle
 */
@Entity
@Table(name = "playlists")
public class Playlist {

  // definition of entity
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long playlistId;

  @Column(name = "owner")
  private Long owner;

  @Column(name = "name")
  private String name;

  @Column(name = "isDefault")
  private Boolean isDefault;

  @OneToMany(mappedBy = "playlist")
  private Set<PlaylistToSubscriber> playlistToSubscriber;

  @OneToMany(mappedBy = "playlist")
  private Set<PlaylistToSongs> playlistToSongs;

  @OneToMany(mappedBy = "playlist")
  private Set<AnalyticsPlaylist> analyticsPlaylists;


  // constructors
  // add default constructor for sake of JPA
  protected Playlist() {
  }

  public Playlist(Long playlistId, Long owner, String name, Boolean isDefault) {
    this.playlistId = playlistId;
    this.owner = owner;
    this.name = name;
    this.isDefault = isDefault;
  }

  public Playlist(Long owner, String name, Boolean isDefault) {
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

  public Long getOwner() {
    return owner;
  }

  public void setOwner(Long owner) {
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
    return "Playlist{"
        + "PlaylistId=" + playlistId
        + ", owner='" + owner + '\''
        + ", name='" + name + '\''
        + ", isDefault=" + isDefault
        + '}';
  }

}
