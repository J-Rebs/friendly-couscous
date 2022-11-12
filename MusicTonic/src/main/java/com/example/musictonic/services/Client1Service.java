package com.example.musictonic.services;

import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.AnalyticsPlaylist;
import com.example.musictonic.model.AnalyticsSong;
import com.example.musictonic.model.AnalyticsUser;
import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.PlaylistToSongs;
import com.example.musictonic.model.PlaylistToSubscriber;
import com.example.musictonic.model.Song;
import com.example.musictonic.model.User;
import com.example.musictonic.model.UserType;
import com.example.musictonic.repository.AnalyticsPlaylistRepository;
import com.example.musictonic.repository.AnalyticsRepository;
import com.example.musictonic.repository.AnalyticsSongRepository;
import com.example.musictonic.repository.AnalyticsUserRepository;
import com.example.musictonic.repository.PlaylistRepository;
import com.example.musictonic.repository.PlaylistToSongRepository;
import com.example.musictonic.repository.PlaylistToSubscriberRepository;
import com.example.musictonic.repository.SongRepository;
import com.example.musictonic.repository.UserRepository;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 *
 *  WANT TO HAVE:
 *  - ability to modify and retrieve (e.g., registering users, making groups, etc. etc.) from
 *    database as well as a function to:
 *  - like a song (creates if not exists default playlist by user and adds to that),
 *  - play a song (triggers an entry in analytics)
 *  - maybe nice to have authorization in round 2 if we have time
 *  DEPEND ON:
 *  - finish the models,
 *  - exceptions,
 *  - controllers, and repositories for rest of our ER diagram,
 *  - write a function for liking a song and playing a song
 *
 *  USER: Johnny --> CLIENT INTERFACE: Spotify <---> MusicTonic <---> communicates with database
 *  Johnny playing a song ~ in postman to doing a post route for a song
 *
 * */

/**
 * Class for Client1Service.
 */
@Service
public class Client1Service {
  // Repositories - analytics, song, user
  @Autowired
  UserRepository userRepo;
  @Autowired
  AnalyticsRepository analyticsRepo;

  @Autowired
  AnalyticsSongRepository analyticsSongRepo;

  @Autowired
  AnalyticsUserRepository analyticsUserRepo;

  @Autowired
  AnalyticsPlaylistRepository analyticsPlaylistRepo;

  @Autowired
  SongRepository songRepo;

  @Autowired
  PlaylistRepository playlistRepo;

  @Autowired
  PlaylistToSongRepository playlistToSongsRepo;

  @Autowired
  PlaylistToSubscriberRepository playlistToSubscriberRepo;


  private Playlist subscribeDefaultPlaylist(Long userId) {

    // see if the user has a default playlist
    Playlist defaultPlaylist = null;
    for (Playlist p : playlistRepo.findAllByOwner(userId)) {
      if (p.getDefault()) {
        defaultPlaylist = p;
        break;
      }
    }

    // if the user doesn't have a default playlist, make one, otherwise return the default playlist
    if (defaultPlaylist == null) {
      defaultPlaylist = new Playlist(userId, "DefaultPlaylist", true);
      // save the playlist to the appropriate table
      /* NOTE: not saving to playlistToSubscribers because by default owner is not subscriber
       * for simplicity
       */
      playlistRepo.save(defaultPlaylist);


    }

    return defaultPlaylist;
  }

  /**
   * Method to like a song.
   *
   * @param userId - the unique ID for this client (i.e., user)
   * @param songId - the unique ID for this song
   * @return the count of liked songs
   * @throws IllegalAccessException if the song is null (i.e., not in the database)
   */
  public Integer likeSong(Long userId, Long songId) throws IllegalAccessException {
    // try fetching the requested song
    Song likedSong = songRepo.findBySongId(songId);
    // if the song doesn't exist thrown an exception
    if (likedSong == null) {
      throw new IllegalAccessException("song cannot be null");
    }
    // otherwise fetch or create the default playlist
    Playlist defaultPlaylist = subscribeDefaultPlaylist(userId);

    // register the song-playlist combination if it doesn't already exist
    if (playlistToSongsRepo.findBySong(likedSong) == null
        && playlistToSongsRepo.findByPlaylist(defaultPlaylist) == null) {
      PlaylistToSongs registeredSong = new PlaylistToSongs(likedSong, defaultPlaylist);
      playlistToSongsRepo.save(registeredSong);
    }

    // increment like count on song
    Integer songLikesCount = likedSong.getSongLikesCount();
    songLikesCount += 1;
    likedSong.setSongLikesCount(songLikesCount);
    songRepo.save(likedSong);

    // return the current liked songs count
    return songLikesCount;
  }

  /**
   * Method to play a song.
   *
   * @param userId     - the unique ID for this client (i.e., user)
   * @param songId     - the unique ID for this song
   * @param playlistId - the unique ID for this playlist
   * @return the Analytics entry/object that was created and added to the Analytics table
   */
  // post operation -- play songs
  public Analytics playSong(Long userId, Long songId, Long playlistId) {

    // insert into analytics (or returns the id)
    Date date = new Date();
    Timestamp timestamp = new Timestamp(date.getTime());
    // Src: https://www.baeldung.com/java-string-to-timestamp
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    String timestampString = formatter.format(timestamp.toLocalDateTime());

    Analytics a = analyticsRepo.save(new Analytics(timestampString));

    // insert into analytics song with the parameters of song id and new analytics id
    Song song = songRepo.findBySongId(songId);
    AnalyticsSong analyticsSong = new AnalyticsSong(a, song);
    analyticsSongRepo.save(analyticsSong);

    // insert into analytics user with the parameters of user id and new analytics id
    User user = userRepo.findByUserId(userId);
    AnalyticsUser analyticsUser = new AnalyticsUser(a, user);
    analyticsUserRepo.save(analyticsUser);

    // insert into analytics playlist with the parameters of playlist id and new analytics id
    Playlist playlist = playlistRepo.findByPlaylistId(playlistId);
    AnalyticsPlaylist analyticsPlaylist = new AnalyticsPlaylist(a, playlist);
    analyticsPlaylistRepo.save(analyticsPlaylist);

    try {
      a.setAnalyticsUser(analyticsUser);
      a.setAnalyticsSong(analyticsSong);
      a.setAnalyticsPlaylist(analyticsPlaylist);
    } catch (Exception e) {
      return a;
    }

    return a;
  }


  /**
   * Method to return a list of all users.
   *
   * @return a list of all users
   */

  // Src: https://github.com/rcoppy/demo-persistent-data-api/blob/main/src/main/java/com/alexrupp/persistentdataapi/controllers/ChatUserController.java
  public List<User> getAllUsers() {
    List<User> l = new ArrayList<>();
    for (User u : userRepo.findAll()) {
      l.add(u);
    }
    return l;
  }

  /**
   * Method to create a user.
   *
   * @param realName  - the name of the user (i.e., Sam)
   * @param type      - user is one of the following types: ARTIST, LISTENER, ADMIN, SCIENTIST
   * @param mainGenre - The main genre for a user, (i.e., country, pop, rap, etc...)
   * @param age       - the age of the user
   * @return the newly created User object that was added to the User table
   */

  public User createUser(String realName, UserType type, String mainGenre, Integer age) {
    try {
      User user = userRepo.save(new User(realName, type, mainGenre, age));
      return user;
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * Method to delete a user. Also deletes all playlists where user is owner, removes
   * user as subscriber from playlists where not owner and deletes all analytics entries
   * for said user.
   *
   * @param userId - the Id of the user to be deleted
   * @return the User object corresponding to the deleted entry in the User table
   */
  public User deleteUser(Long userId) {
    try {
      // get user to be deleted
      User toDelete = userRepo.findByUserId(userId);

      // find and delete all corresponding entries for user related to analytics
      // first, get a list of all analyticsUser entries
      List<AnalyticsUser> analyticsUserList = analyticsUserRepo.findByUser(toDelete);
      for (AnalyticsUser au : analyticsUserList) {
        // then get the analytics entity for all entries found
        Analytics a = au.getAnalytics();
        // try to delete analyticsUser
        analyticsUserRepo.deleteByAnalytics(a);
        // try to delete analyticsPlaylist
        analyticsPlaylistRepo.deleteByAnalytics(a);
        // try to delete analyticsSong
        analyticsSongRepo.deleteByAnalytics(a);
        // then delete analytics entry
        analyticsRepo.delete(a);
      }


      // find and delete all corresponding entries for a user related to playlistToSubscriber
      List<PlaylistToSubscriber> playlistToSubscriberList =
          playlistToSubscriberRepo.findAllByUser(toDelete);
      for (PlaylistToSubscriber ps : playlistToSubscriberList) {
        playlistToSubscriberRepo.delete(ps);
      }

      // find and delete all playlists where user is the owner
      List<Playlist> userOwnedPlaylists = playlistRepo.findAllByOwner(userId);
      for (Playlist p : userOwnedPlaylists) {
        // also need to remove all the songs in the playlist, along with any subscriber entries,
        // AND all analytics entries
        // subscribers
        List<PlaylistToSubscriber> subscriberList =
            playlistToSubscriberRepo.findAllByPlaylist(p);

        for (PlaylistToSubscriber ps : subscriberList) {
          playlistToSubscriberRepo.delete(ps);
        }
        // songs
        List<PlaylistToSongs> songList =
            playlistToSongsRepo.findAllByPlaylist(p);
        for (PlaylistToSongs ps : songList) {
          playlistToSongsRepo.delete(ps);
        }
        // analytics
        List<AnalyticsPlaylist> analyticsList =
            analyticsPlaylistRepo.findAllByPlaylist(p);
        for (AnalyticsPlaylist ap : analyticsList) {
          analyticsPlaylistRepo.delete(ap);
        }
        // the can delete the playlist itself
        playlistRepo.delete(p);
      }

      // delete the user from database and return the deleted user object
      userRepo.delete(toDelete);
      return toDelete;
    } catch (Exception e) {
      return null;
    }
  }
}
