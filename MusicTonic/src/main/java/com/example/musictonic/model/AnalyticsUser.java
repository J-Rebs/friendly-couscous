package com.example.musictonic.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "analytics_user")
public class AnalyticsUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    Long id;

    @ManyToOne
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
        return "AnalyticsUser{" +
                "id=" + id +
                ", analytics=" + analytics +
                ", user=" + user +
                '}';
    }
}
