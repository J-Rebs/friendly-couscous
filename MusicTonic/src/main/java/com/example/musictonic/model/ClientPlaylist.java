package com.example.musictonic.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "client_playlist")
public class ClientPlaylist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    Long id;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "playlistId")
    private Playlist playlist;

    protected ClientPlaylist() {
    }

    public ClientPlaylist(Long id, Client client, Playlist playlist) {
        this.id = id;
        this.client = client;
        this.playlist = playlist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public String toString() {
        return "ClientPlaylist{" +
                "id=" + id +
                ", client=" + client +
                ", playlist=" + playlist +
                '}';
    }
}
