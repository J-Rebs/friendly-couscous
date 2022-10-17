package com.example.musictonic.model;

import javax.persistence.*;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long song_UID;

    @Column(name = "song_name")
    private String song_name;

    @Column(name = "song_duration")
    private Long song_duration;

    @Column(name = "song_artist")
    private String song_artist;

    @Column(name = "song_lyrics")
    private String song_lyrics;

    @Column(name = "song_likes_count")
    private int song_likes_count;

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public Long getSong_duration() {
        return song_duration;
    }

    public void setSong_duration(Long song_duration) {
        this.song_duration = song_duration;
    }

    public String getSong_artist() {
        return song_artist;
    }

    public void setSong_artist(String song_artist) {
        this.song_artist = song_artist;
    }

    public String getSong_lyrics() {
        return song_lyrics;
    }

    public void setSong_lyrics(String song_lyrics) {
        this.song_lyrics = song_lyrics;
    }

    public int getSong_likes_count() {
        return song_likes_count;
    }

    public void setSong_likes_count(int song_likes_count) {
        this.song_likes_count = song_likes_count;
    }

    public Song() {

    }

    @Override
    public String toString() {
        return "Song{" +
                "song_UID=" + song_UID +
                ", song_name='" + song_name + '\'' +
                ", song_duration=" + song_duration +
                ", song_artist='" + song_artist + '\'' +
                ", song_lyrics='" + song_lyrics + '\'' +
                ", song_likes_count=" + song_likes_count +
                '}';
    }

    public Song(String song_name, Long song_duration, String song_artist, String song_lyrics, int song_likes_count) {
        this.song_name = song_name;
        this.song_duration = song_duration;
        this.song_artist = song_artist;
        this.song_lyrics = song_lyrics;
        this.song_likes_count = song_likes_count;
    }

}
