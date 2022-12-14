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
 * <h3>This is the song entity.</h3>
 * Note: required for compliance with checkstyle
 */
@Entity
@Table(name = "songs")
public class Song {

  // definition of entity
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long songId;

  @Column(name = "song_name")
  private String songName;

  @Column(name = "song_duration")
  private Integer songDuration;

  @Column(name = "song_artist")
  private String songArtist;

  @Column(name = "song_lyrics")
  private String songLyrics;

  @Column(name = "song_likes_count")
  private Integer songLikesCount;

  @OneToMany(mappedBy = "song")
  private Set<PlaylistToSongs> playlistToSongs;

  @OneToMany(mappedBy = "song")
  private Set<AnalyticsSong> analyticsSongs;


  // constructors
  protected Song() {
  }

  /**
   * Parameterized constructor for Song object.
   *
   * @param songName       - Name of song
   * @param songDuration   - Duration of song
   * @param songArtist     - Artist of song
   * @param songLyrics     - Lyrics of song
   * @param songLikesCount - Number of users that like this song
   */
  public Song(String songName, Integer songDuration, String songArtist, String songLyrics,
              Integer songLikesCount) {
    this.songName = songName;
    this.songDuration = songDuration;
    this.songArtist = songArtist;
    this.songLyrics = songLyrics;
    this.songLikesCount = songLikesCount;
  }

  /**
   * Parameterized constructor for Song object.
   *
   * @param songId         - the unique ID for this Song object
   * @param songName       - Name of song
   * @param songDuration   - Duration of song
   * @param songArtist     - Artist of song
   * @param songLyrics     - Lyrics of song
   * @param songLikesCount - Number of users that like this song
   */
  public Song(Long songId, String songName, Integer songDuration, String songArtist,
              String songLyrics, Integer songLikesCount) {
    this.songId = songId;
    this.songName = songName;
    this.songDuration = songDuration;
    this.songArtist = songArtist;
    this.songLyrics = songLyrics;
    this.songLikesCount = songLikesCount;
  }

  // getters and setters
  public Long getSongId() {
    return songId;
  }

  public void setSongId(Long songId) {
    this.songId = songId;
  }

  public String getSongName() {
    return songName;
  }

  public void setSongName(String songName) {
    this.songName = songName;
  }

  public Integer getSongDuration() {
    return songDuration;
  }

  public void setSongDuration(Integer songDuration) {
    this.songDuration = songDuration;
  }

  public String getSongArtist() {
    return songArtist;
  }

  public void setSongArtist(String songArtist) {
    this.songArtist = songArtist;
  }

  public String getSongLyrics() {
    return songLyrics;
  }

  public void setSongLyrics(String songLyrics) {
    this.songLyrics = songLyrics;
  }

  public Integer getSongLikesCount() {
    return songLikesCount;
  }

  public void setSongLikesCount(Integer songLikesCount) {
    this.songLikesCount = songLikesCount;
  }

  @Override
  public String toString() {
    return "Song{"
        + "songId=" + songId
        + ", songName='" + songName + '\''
        + ", songDuration=" + songDuration
        + ", songArtist='" + songArtist + '\''
        + ", songLyrics='" + songLyrics + '\''
        + ", songLikesCount=" + songLikesCount
        + '}';
  }
}
