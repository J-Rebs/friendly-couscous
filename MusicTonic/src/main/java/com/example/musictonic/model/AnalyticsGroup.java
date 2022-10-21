package com.example.musictonic.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "analytics_song")
public class AnalyticsGroup implements Serializable {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "analyticsId")
    private Analytics analytics;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;
}
