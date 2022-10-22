package com.example.musictonic.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "playlist_to_songs")
public class PlaylistToSongs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "songId", nullable = true)
    private Song song;

    @ManyToOne
    @JoinColumn(name = "playlistId", nullable = true)
    private Playlist playlist;

    protected PlaylistToSongs() {
    }

    public PlaylistToSongs(Long id, Song song, Playlist playlist) {
        this.id = id;
        this.song = song;
        this.playlist = playlist;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public String toString() {
        return "PlaylistToSongs{" +
                "id=" + id +
                ", song=" + song +
                ", playlist=" + playlist +
                '}';
    }
}
