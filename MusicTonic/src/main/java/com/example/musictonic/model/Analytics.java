package com.example.musictonic.model;


import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "analytics")
public class Analytics {

  // definition of entity
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "serial")
  private Long analyticsId;

  @Column(name = "timestamp")
  private java.sql.Timestamp timestamp;

  @OneToOne(mappedBy = "analytics")
  private AnalyticsSong analyticsSong;

  @OneToOne(mappedBy = "analytics")
  private AnalyticsPlaylist analyticsPlaylist;

  @OneToOne(mappedBy = "analytics")
  private AnalyticsUser analyticsUser;

  @OneToOne(mappedBy = "analytics")
  private AnalyticsGroup analyticsGroup;

  // constructors
  protected Analytics() {
  }

  public Analytics(Long analyticsId, Timestamp timestamp, AnalyticsSong analyticsSong,
                   AnalyticsPlaylist analyticsPlaylist, AnalyticsUser analyticsUser,
                   AnalyticsGroup analyticsGroup) {
    this.analyticsId = analyticsId;
    this.timestamp = timestamp;
    this.analyticsSong = analyticsSong;
    this.analyticsPlaylist = analyticsPlaylist;
    this.analyticsUser = analyticsUser;
    this.analyticsGroup = analyticsGroup;
  }

  public Analytics(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public Long getAnalyticsId() {
    return analyticsId;
  }

  public void setAnalyticsId(Long analyticsId) {
    this.analyticsId = analyticsId;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public AnalyticsSong getAnalyticsSong() {
    return analyticsSong;
  }

  public void setAnalyticsSong(AnalyticsSong analyticsSong) {
    this.analyticsSong = analyticsSong;
  }

  public AnalyticsPlaylist getAnalyticsPlaylist() {
    return analyticsPlaylist;
  }

  public void setAnalyticsPlaylist(AnalyticsPlaylist analyticsPlaylist) {
    this.analyticsPlaylist = analyticsPlaylist;
  }

  public AnalyticsUser getAnalyticsUser() {
    return analyticsUser;
  }

  public void setAnalyticsUser(AnalyticsUser analyticsUser) {
    this.analyticsUser = analyticsUser;
  }

  public AnalyticsGroup getAnalyticsGroup() {
    return analyticsGroup;
  }

  public void setAnalyticsGroup(AnalyticsGroup analyticsGroup) {
    this.analyticsGroup = analyticsGroup;
  }

  @Override
  public String toString() {
    return "Analytics{"
        + "analyticsId=" + analyticsId
        + ", timestamp=" + timestamp
        + ", analyticsSong=" + analyticsSong
        + ", analyticsPlaylist=" + analyticsPlaylist
        + ", analyticsUser=" + analyticsUser
        + ", analyticsGroup=" + analyticsGroup
        + '}';
  }
}
