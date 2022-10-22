package com.example.musictonic.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "playlist_to_subscriber")
public class PlaylistToSubscriber implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    Long id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "playlistId", nullable = true)
    private Playlist playlist;

    protected PlaylistToSubscriber() {

    }





}
