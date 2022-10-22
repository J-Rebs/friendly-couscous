package com.example.musictonic.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

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

    @OneToMany(mappedBy = "analytics")
    private Set<AnalyticsSong> analyticsSong;

    @OneToMany(mappedBy = "analytics")
    private Set<AnalyticsPlaylist> analyticsPlaylist;

    @OneToMany(mappedBy = "analytics")
    private Set<AnalyticsUser> analyticsUser;

    @OneToMany(mappedBy = "analytics")
    private Set<AnalyticsGroup> analyticsGroup;

    // constructors
    protected Analytics() {
    }

    public Analytics(Long analyticsId, Timestamp timestamp, Set<AnalyticsSong> analyticsSong, Set<AnalyticsPlaylist> analyticsPlaylist, Set<AnalyticsUser> analyticsUser, Set<AnalyticsGroup> analyticsGroup) {
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

    public Set<AnalyticsSong> getAnalyticsSong() {
        return analyticsSong;
    }

    public void setAnalyticsSong(Set<AnalyticsSong> analyticsSong) {
        this.analyticsSong = analyticsSong;
    }

    public Set<AnalyticsPlaylist> getAnalyticsPlaylist() {
        return analyticsPlaylist;
    }

    public void setAnalyticsPlaylist(Set<AnalyticsPlaylist> analyticsPlaylist) {
        this.analyticsPlaylist = analyticsPlaylist;
    }

    public Set<AnalyticsUser> getAnalyticsUser() {
        return analyticsUser;
    }

    public void setAnalyticsUser(Set<AnalyticsUser> analyticsUser) {
        this.analyticsUser = analyticsUser;
    }

    public Set<AnalyticsGroup> getAnalyticsGroup() {
        return analyticsGroup;
    }

    public void setAnalyticsGroup(Set<AnalyticsGroup> analyticsGroup) {
        this.analyticsGroup = analyticsGroup;
    }

    @Override
    public String toString() {
        return "Analytics{" +
                "analyticsId=" + analyticsId +
                ", timestamp=" + timestamp +
                ", analyticsSong=" + analyticsSong +
                ", analyticsPlaylist=" + analyticsPlaylist +
                ", analyticsUser=" + analyticsUser +
                ", analyticsGroup=" + analyticsGroup +
                '}';
    }
}

