package com.example.musictonic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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

  public String getPassword() {
    return password;
  }
}
