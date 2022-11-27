package com.example.musictonic.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * <h3>This is the client entity.</h3>
 * Note: required for compliance with checkstyle
 */
@Entity
@Table(name = "clients")
public class Client {

  // definition of entity
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  public Client(Long clientId) {
    this.clientId = clientId;
  }

  public Long getClientId() {
    return clientId;
  }

}
