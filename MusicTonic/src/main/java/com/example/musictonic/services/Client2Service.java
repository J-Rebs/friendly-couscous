package com.example.musictonic.services;

/*
 *
 *  WANT TO HAVE:
 *  - want a few analytics functions like
 *  - top 10 most played songs (if we have time, will add ability to do by timeframe)
 *  - average # of songs per user played per day
 *  - top 5 most subscribed playlists (tbd if have time)
 *  DEPEND ON:
 *  - having functional database
 *  - loading some example data
 * */

import com.example.musictonic.Utils.PopularSongsReturn;
import com.example.musictonic.model.Song;
import com.example.musictonic.repository.PlaylistToSongRepository;
import com.example.musictonic.repository.SongRepository;
import com.example.musictonic.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class for Client2Service.
 */
@Service
public class Client2Service {
  @Autowired
  SongRepository songRepo;

  @Autowired
  UserRepository userRepo;

  @Autowired
  PlaylistToSongRepository playlistToSongRepo;

  /**
   * Method to return the top three most popular songs.
   *
   * @return ArrayList consisting of the top three most popular songs
   */
  public PopularSongsReturn getMostPopularSongs() {
    // get all songs in order of popularity
    List<Song> allSongs = songRepo.findTop3ByOrderBySongLikesCountDesc();
    // keep only the top 3
    List<Song> top3Songs = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      if (i < allSongs.size()) {
        top3Songs.add(allSongs.get(i));
      }
    }
    // for each song count how many playlists they appear in, return the average
    Double totalCount = 0.0;
    for (Song s : top3Songs) {
      totalCount += playlistToSongRepo.findAllBySong(s).size();
    }

    Double averagePlaylists = totalCount / 3;

    return new PopularSongsReturn(top3Songs, averagePlaylists);
  }


}
