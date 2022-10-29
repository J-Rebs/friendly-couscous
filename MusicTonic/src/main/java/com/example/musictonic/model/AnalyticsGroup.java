package com.example.musictonic.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * <h3>This is the analytics group entity.</h3>
 * Note: required for compliance with checkstyle
 */
@Entity
@Table(name = "analytics_group")
public class AnalyticsGroup implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @OneToOne
  @JoinColumn(name = "analyticsId")
  private Analytics analytics;

  @ManyToOne
  @JoinColumn(name = "groupId")
  private Group group;

  protected AnalyticsGroup() {

  }

  /**
   * Parameterized constructor for the class AnalyticsGroup.
   *
   * @param id - the unique ID for this AnalyticsGroup entry
   * @param analytics - the corresponding entity for Analytics
   * @param group - the corresponding entity for Group
   */
  public AnalyticsGroup(Long id, Analytics analytics, Group group) {
    this.id = id;
    this.analytics = analytics;
    this.group = group;
  }
}
