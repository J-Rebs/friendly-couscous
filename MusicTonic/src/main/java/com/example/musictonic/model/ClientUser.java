package com.example.musictonic.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * <h3>This is the client user entity.</h3>
 * Note: required for compliance with checkstyle
 */
@Entity
@Table(name = "client_user")
public class ClientUser implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne
  @JoinColumn(name = "clientId")
  private Client client;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  protected ClientUser() {
  }

  /**
   * Parameterized constructor for ClientUser.
   *
   * @param id     - the unique ID for this ClientUser entry
   * @param client - the Client object
   * @param user   - the User object
   */
  public ClientUser(Long id, Client client, User user) {
    this.id = id;
    this.client = client;
    this.user = user;
  }

  public ClientUser(Client client, User user) {
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
    return "ClientUser{"
        + "id=" + id
        + ", client=" + client
        + ", user=" + user
        + '}';
  }

}
