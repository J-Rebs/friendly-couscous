package com.example.musictonic.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "analytics_group")
public class ClientUser implements Serializable {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    protected ClientUser() {
    }

    public ClientUser(Long id, Client client, User user) {
        this.id = id;
        this.client = client;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ClientUser{" +
                "id=" + id +
                ", client=" + client +
                ", user=" + user +
                '}';
    }

}
