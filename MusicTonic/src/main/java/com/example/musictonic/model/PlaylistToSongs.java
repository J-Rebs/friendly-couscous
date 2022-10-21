package com.example.musictonic.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "playlist_to_songs")
public class PlaylistToSongs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long playlistId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long songId;

    @ManyToOne
    @JoinColumn(name = "songId", nullable = true)
    private Song song;

    @ManyToOne
    @JoinColumn(name = "playlistId", nullable = true)
    private Playlist playlist;

    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
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
                "playlistId=" + playlistId +
                ", songId=" + songId +
                ", song=" + song +
                ", playlist=" + playlist +
                '}';
    }
    
}
