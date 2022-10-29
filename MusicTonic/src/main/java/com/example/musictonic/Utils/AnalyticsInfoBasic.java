package com.example.musictonic.Utils;

import com.example.musictonic.model.User;

/**
 * Class to condense the analytics history for a given user.
 */
public class AnalyticsInfoBasic {
  private Long analyticsId;
  private String timestampString;
  private User user;

  protected AnalyticsInfoBasic() {

  }

  /**
   * Parameterized constructor for AnalyticsInfoBasic.
   *
   * @param analyticsId - the unique ID for the analytics entry
   * @param timestampString - the timestamp of the analytics entry
   * @param user - the User object corresponding to the Analytics entry
   */
  public AnalyticsInfoBasic(Long analyticsId, String timestampString, User user) {
    this.analyticsId = analyticsId;
    this.timestampString = timestampString;
    this.user = user;
  }

  public Long getAnalyticsId() {
    return analyticsId;
  }

  public void setAnalyticsId(Long analyticsId) {
    this.analyticsId = analyticsId;
  }

  public String getTimestampString() {
    return timestampString;
  }

  public void setTimestampString(String timestampString) {
    this.timestampString = timestampString;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }


}
