package com.example.musictonic.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * <h3>This is the analytics user entity.</h3>
 * Note: required for compliance with checkstyle
 */
@Entity
@Table(name = "analytics_user")
public class AnalyticsUser implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "serial")
  Long id;

  @OneToOne
  @JoinColumn(name = "analyticsId")
  private Analytics analytics;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  protected AnalyticsUser() {

  }

  public AnalyticsUser(Long id, Analytics analytics, User user) {
    this.id = id;
    this.analytics = analytics;
    this.user = user;
  }

  public AnalyticsUser(Analytics analytics, User user) {
    this.analytics = analytics;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Analytics getAnalytics() {
    return analytics;
  }

  public void setAnalytics(Analytics analytics) {
    this.analytics = analytics;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "AnalyticsUser{"
        + "id=" + id
        + ", analytics=" + analytics
        + ", user=" + user
        + '}';
  }
}
