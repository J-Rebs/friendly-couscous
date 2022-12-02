package com.example.musictonic.utils;

import com.example.musictonic.model.Song;
import java.util.List;

/**
 * Class for PopularSongsReturn.
 */
public class SongDurationAndSizeReturn {
  private Integer numberOfSongs;

  private Integer totalDuration;


  protected SongDurationAndSizeReturn() {

  }

  public SongDurationAndSizeReturn(Integer numberOfSongs, Integer totalDuration) {
    this.numberOfSongs = numberOfSongs;
    this.totalDuration = totalDuration;
  }

  public Integer getNumberOfSongs() {
    return numberOfSongs;
  }

  public void setNumberOfSongs(Integer numberOfSongs) {
    this.numberOfSongs = numberOfSongs;
  }

  public Integer getTotalDuration() {
    return totalDuration;
  }

  public void setTotalDuration(Integer totalDuration) {
    this.totalDuration = totalDuration;
  }
}
