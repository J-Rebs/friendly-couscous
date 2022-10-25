package com.example.musictonic.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "songs")
public class Song {

  // definition of entity
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "serial")
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

  public Song(String songName, Integer songDuration, String songArtist, String songLyrics,
              Integer songLikesCount) {
    this.songName = songName;
    this.songDuration = songDuration;
    this.songArtist = songArtist;
    this.songLyrics = songLyrics;
    this.songLikesCount = songLikesCount;
  }

  public Song(Long songId, String songName, Integer songDuration, String songArtist,
              String songLyrics, Integer songLikesCount) {
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
