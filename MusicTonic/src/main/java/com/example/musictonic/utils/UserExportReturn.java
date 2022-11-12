package com.example.musictonic.utils;

import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.User;
import java.util.List;

/**
 * Class for UserExportReturn.
 */
public class UserExportReturn {
  private User user;
  private List<Playlist> playlistList;
  private List<AnalyticsInfoBasic> analyticsList;

  protected UserExportReturn() {

  }

  /**
   * Parameterized constructor for UserExportReturn object.
   *
   * @param user          - the user object that this object is being generated for
   * @param playlistList  - list of playlists associated with this user
   * @param analyticsList - list of analytics associated with this user
   */
  public UserExportReturn(User user, List<Playlist> playlistList,
                          List<AnalyticsInfoBasic> analyticsList) {
    this.user = user;
    this.playlistList = playlistList;
    this.analyticsList = analyticsList;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Playlist> getPlaylistList() {
    return playlistList;
  }

  public void setPlaylistList(List<Playlist> playlistList) {
    this.playlistList = playlistList;
  }

  public List<AnalyticsInfoBasic> getAnalyticsList() {
    return analyticsList;
  }

  public void setAnalyticsList(List<AnalyticsInfoBasic> analyticsList) {
    this.analyticsList = analyticsList;
  }

  @Override
  public String toString() {
    return "UserExportReturn{"
        + "user=" + user
        + ", playlistList=" + playlistList
        + ", analyticsList=" + analyticsList
        + '}';
  }

}
