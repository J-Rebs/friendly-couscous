package com.example.musictonic.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "songs")
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


}

