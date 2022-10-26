package com.example.musictonic.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * <h3>This is the client group entity.</h3>
 * Note: required for compliance with checkstyle
 */
@Entity
@Table(name = "client_group")
public class ClientGroup implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "serial")
  Long id;

  @ManyToOne
  @JoinColumn(name = "clientId")
  private Client client;

  @ManyToOne
  @JoinColumn(name = "groupId")
  private Group group;

  protected ClientGroup() {
  }

  public ClientGroup(Long id, Client client, Group group) {
    this.id = id;
    this.client = client;
    this.group = group;
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

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  @Override
  public String toString() {
    return "ClientGroup{"
        + "id=" + id
        + ", client=" + client
        + ", group=" + group
        + '}';
  }


}
