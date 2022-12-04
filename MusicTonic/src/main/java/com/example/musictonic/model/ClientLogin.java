package com.example.musictonic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <h3>This is the client login entity.</h3>
 * Note: required for compliance with checkstyle.
 * Client logins correspond to when a client manually entered into the MusicTonic
 * DB choose to sign up for authentication.
 * This prevents clients from signing-up who the MusicTonic owners don't want to have access.
 */
@Entity
public class ClientLogin {

  @Id
  @Column(name = "client_name", unique = true)
  private String clientName;

  @Column(name = "password", unique = true)
  private String password;


  public ClientLogin(String clientName, String password) {
    this.clientName = clientName;
    this.password = password;
  }

  public ClientLogin() {

  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
