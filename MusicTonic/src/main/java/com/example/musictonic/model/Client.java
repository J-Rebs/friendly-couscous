package com.example.musictonic.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

  @Column(name = "client_name")
  private String clientName;

  // @OneToOne(fetch = FetchType.LAZY)
  // @MapsId
  // private ClientLogin clientLogin;

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

  public Client(String clientName) {
    this.clientName = clientName;
  }

  public Client(Long clientId) {
    this.clientId = clientId;
  }

  public Long getClientId() {
    return clientId;
  }

}
