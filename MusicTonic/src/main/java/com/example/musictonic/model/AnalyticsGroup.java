package com.example.musictonic.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "analytics_group")
public class AnalyticsGroup implements Serializable {

  @Id
  Long id;

  @OneToOne
  @JoinColumn(name = "analyticsId")
  private Analytics analytics;

  @ManyToOne
  @JoinColumn(name = "groupId")
  private Group group;

  protected AnalyticsGroup() {

  }

  public AnalyticsGroup(Long id, Analytics analytics, Group group) {
    this.id = id;
    this.analytics = analytics;
    this.group = group;
  }
}
