package com.example.musictonic.controller;

import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.User;
import com.example.musictonic.repository.UserRepository;
import com.example.musictonic.services.Client1Service;
import com.example.musictonic.utils.PlaySongReturn;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


//import org.springframework.boot.configurationprocessor.json;
//import org.springframework.web.bind.annotation.PostMapping;

/**
 * Request handler for client type 1 (e.g., CRUD functionality).
 */
@RestController
@RequestMapping("client1-rest")
public class Client1Controller {
  // [1.1, 1.2] Import our service - business functionality - playSong, subscribeToPlaylist
  // Autowired client1Controller
  // Implement routes
  // POST request for playing song
  // POST request for subscribing to a playlist
  @Autowired
  Client1Service client1Service;

  @Autowired
  UserRepository userRepo;


  // Src: https://github.com/rcoppy/demo-persistent-data-api/blob/main/src/main/java/com/alexrupp/persistentdataapi/controllers/ChatUserController.java
  // Purpose: check if users are present before testing post routes
  @GetMapping(value = "/playsong")
  List<String> playSong() {

    List<String> l = new ArrayList<>();

    for (User u : userRepo.findAll()) {
      l.add(u.getRealName());
    }

    return l;
  }

  // reference: https://www.baeldung.com/spring-request-param

  /**
   * This method is called when the client "plays a song," such that it invokes the client1Service
   * method playSong(...) and returns the associated analyticsId.
   *
   * @param userId - the unique ID for this client (i.e., user)
   * @param songId - the unique ID for this song
   * @param playlistId - the unique ID for this song
   * @return a PlaySongReturn object, which consists of the analyticsId and the timestamp
   */
  @PostMapping("/playsong")
  @ResponseBody
  public ResponseEntity<PlaySongReturn> createAnalyticalSongs(
      @RequestParam(name = "userid") String userId,
      @RequestParam(name = "songid") String songId,
      @RequestParam(name = "playlistid") String playlistId) {
    try {
      Analytics analytics = client1Service.playSong(Long.parseLong(userId), Long.parseLong(songId),
          Long.parseLong(playlistId));
      PlaySongReturn response =
          new PlaySongReturn(analytics.getAnalyticsId(), analytics.getTimestamp());
      return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * This method is invoked when the client "likes a song," such that it adds the corresponding
   * song to the client's default "likes" playlist (to be implemented) and returns the (updated)
   * number of liked songs associated with this client.
   *
   * @param userId - the unique ID for this client (i.e., user)
   * @param songId - the unique ID for this song
   * @return - if OK, then the number of liked songs; else, BAD_REQUEST
   */
  @PutMapping("/likeSong")
  @ResponseBody
  public ResponseEntity<Integer> likeSong(
      @RequestParam(name = "userid") String userId,
      @RequestParam(name = "songid") String songId) {
    try {
      Integer likedSongCount =
          client1Service.likeSong(Long.parseLong(userId), Long.parseLong(songId));
      return new ResponseEntity<>(likedSongCount, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }

  // for testing - list all users
  @GetMapping(value = "/listUsers")
  List<String> listUsers() {
    List<User> l = new ArrayList<>();
    for (User u : userRepo.findAll()) {
      l.add(u);
    }
    return l;
  }

  @PostMapping("/createUser")
  @ResponseBody
  public ResponseEntity<User> createUser(
          @RequestParam(name = "userid") Long userId,
          @RequestParam(name = "realname") String realName,
          @RequestParam(name = "usertype") UserType userType,
          @RequestParam(name = "maingenre") String mainGenre,
          @RequestParam(name = "age") Integer age){
    try {
      User user = userRepo.save(new User(userId, realName, userType, mainGenre, age));
      return new ResponseEntity<>(user, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }
}
