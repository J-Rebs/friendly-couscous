package com.example.musictonic.utils;

/**
 * Class for PlaySongReturn.
 */
public class PlaySongReturn {
  private Long id;
  private String timestamp;

  protected PlaySongReturn() {
  }


  public PlaySongReturn(Long id, String timestamp) {
    this.id = id;
    this.timestamp = timestamp;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }
}
