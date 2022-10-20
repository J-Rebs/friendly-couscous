package com.example.musictonic.model;

import javax.persistence.*;

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
    private UserType songDuration;

    @Column(name = "song_artist")
    private String songArtist;

    @Column(name = "song_lyrics")
    private String songLyrics;

    @Column(name = "song_likes_count")
    private Integer songLikesCount;

    // constructors
    protected Song() {
    }

    public Song(String songName, UserType songDuration, String songArtist, String songLyrics, Integer songLikesCount) {
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

    public UserType getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(UserType songDuration) {
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
        return "Song{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", songDuration=" + songDuration +
                ", songArtist='" + songArtist + '\'' +
                ", songLyrics='" + songLyrics + '\'' +
                ", songLikesCount=" + songLikesCount +
                '}';
    }
}

