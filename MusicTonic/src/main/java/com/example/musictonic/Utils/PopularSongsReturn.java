package com.example.musictonic.Utils;

import com.example.musictonic.model.Song;
import java.util.List;

public class PopularSongsReturn {
  private List<Song> popularSongs;

  private Double averageNumberPlaylists;


  protected PopularSongsReturn() {

  }

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
