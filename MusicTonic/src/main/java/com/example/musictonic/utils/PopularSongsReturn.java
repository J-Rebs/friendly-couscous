package com.example.musictonic.utils;

import com.example.musictonic.model.Song;
import java.util.List;

/**
 * Class for PopularSongsReturn.
 */
public class PopularSongsReturn {
  private List<Song> popularSongs;

  private Double averageNumberPlaylists;


  protected PopularSongsReturn() {

  }

  /**
   * Parameterized constructor for PopularSongsReturn.
   *
   * @param popularSongs - list of Songs consisting of popular songs
   * @param averageNumberPlaylists - the average number of playlists that these songs are on
   */
  public PopularSongsReturn(List<Song> popularSongs, Double averageNumberPlaylists) {
    this.popularSongs = popularSongs;
    this.averageNumberPlaylists = averageNumberPlaylists;
  }

  public List<Song> getPopularSongs() {
    return popularSongs;
  }

  public void setPopularSongs(List<Song> popularSongs) {
    this.popularSongs = popularSongs;
  }

  public Double getAverageNumberPlaylists() {
    return averageNumberPlaylists;
  }

  public void setAverageNumberPlaylists(Double averageNumberPlaylists) {
    this.averageNumberPlaylists = averageNumberPlaylists;
  }


}
