package com.example.musictonic.Utils;

import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.User;
import java.util.List;
import java.util.Set;

public class UserExportReturn {
  private User user;
  private List<Playlist> playlistList;
  private List<AnalyticsInfoBasic> analyticsList;

  protected UserExportReturn() {

  }

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
    return "UserExportReturn{" +
        "user=" + user +
        ", playlistList=" + playlistList +
        ", analyticsList=" + analyticsList +
        '}';
  }

}
