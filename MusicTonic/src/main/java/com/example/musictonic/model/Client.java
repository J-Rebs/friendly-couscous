package com.example.musictonic.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {

    // definition of entity
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long clientId;

    @Column(name = "clientName")
    private String clientName;

    @OneToMany(mappedBy = "client")
    private Set<ClientSong> clientSong;

    @OneToMany(mappedBy = "client")
    private Set<ClientPlaylist> clientPlaylist;

    @OneToMany(mappedBy = "client")
    private Set<ClientUser> clientUser;

    @OneToMany(mappedBy = "client")
    private Set<ClientGroup> clientGroup;

    // constructors
    protected Client() {
    }


}

