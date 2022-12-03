package com.example.musictonic.controller;

import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.User;
import com.example.musictonic.model.UserType;
import com.example.musictonic.repository.UserRepository;
import com.example.musictonic.services.Client1Service;
import com.example.musictonic.utils.PlaySongReturn;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin
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


  // reference: https://www.baeldung.com/spring-request-param

  /**
   * This method is called when the client "plays a song," such that it invokes the client1Service
   * method playSong(...) and returns the associated analyticsId.
   *
   * @param userId     - the unique ID for this client (i.e., user)
   * @param songId     - the unique ID for this song
   * @param playlistId - the unique ID for this song
   * @return a PlaySongReturn object, which consists of the analyticsId and the timestamp
   */
  @PostMapping("/playsong")
  @ResponseBody
  public ResponseEntity<PlaySongReturn> createAnalyticalSongs(
      @RequestParam(name = "userid") String userId,
      @RequestParam(name = "songid") String songId,
      @RequestParam(name = "playlistid") String playlistId,
      @RequestParam(name = "clientid") String clientId) {
    try {
      Analytics analytics = client1Service.playSong(Long.parseLong(userId), Long.parseLong(songId),
          Long.parseLong(playlistId), Long.parseLong(clientId));
      PlaySongReturn response =
          new PlaySongReturn(analytics.getAnalyticsId(), analytics.getTimestamp());
      return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * This method is invoked when the client "likes a song," such that it adds the corresponding
   * song to the client's default "likes" playlist (to be implemented) and returns the (updated)
   * number of liked songs associated with this client.
   *
   * @param userId   - the unique ID for this client (i.e., user)
   * @param songId   - the unique ID for this song
   * @param clientId - the ID for the client in question
   * @return - if OK, then the number of liked songs; else, BAD_REQUEST
   */
  @PutMapping("/likeSong")
  @ResponseBody
  public ResponseEntity<Integer> likeSong(
      @RequestParam(name = "userid") String userId,
      @RequestParam(name = "songid") String songId,
      @RequestParam(name = "clientid") String clientId) {
    try {
      Integer likedSongCount =
          client1Service.likeSong(Long.parseLong(userId), Long.parseLong(songId),
              Long.parseLong(clientId));
      return new ResponseEntity<>(likedSongCount, HttpStatus.OK);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * This method is invoked to return a list of all Users.
   *
   * @param clientId - the ID for the client in question
   * @return - if OK, then a list of all users for a given client; else, BAD_REQUEST
   */
  // for testing - list all users
  @GetMapping("/listUsers")
  public ResponseEntity<List<User>> listUsers(@RequestParam(name = "clientid") String clientId) {
    try {
      Long id = Long.parseLong(clientId);
      List<User> userList = client1Service.getAllUsers(id);
      return new ResponseEntity<>(userList, HttpStatus.OK);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }

  // commenting out for now, don't think we want to give the ability to set the userid because
  // this is an autogenerated primary key -- we can re-add if needed.

  /**
   * This method creates a user for a client.
   *
   * @param realName  - the name of the user (i.e., Sam)
   * @param userType  - user is one of the following types: ARTIST, LISTENER, ADMIN, SCIENTIST
   * @param mainGenre - The main genre for a user, (i.e., country, pop, rap, etc...)
   * @param age       - the age of the user
   * @param clientId  - the ID for the client in question
   * @return - if Created, then return new User; else, BAD_REQUEST
   */

  @PostMapping("/createUser")
  @ResponseBody
  public ResponseEntity<User> createUser(
      @RequestParam(name = "realname") String realName,
      @RequestParam(name = "usertype") String userType,
      @RequestParam(name = "maingenre") String mainGenre,
      @RequestParam(name = "age") String age,
      @RequestParam(name = "clientid") String clientId) {
    try {
      UserType type = UserType.valueOf(userType);
      Integer ageInt = Integer.parseInt(age);
      Long clientIdLong = Long.parseLong(clientId);
      User user = client1Service.createUser(realName, type, mainGenre, ageInt, clientIdLong);
      return new ResponseEntity<>(user, HttpStatus.CREATED);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }


  /**
   * Method to delete a user. Also deletes all playlists where user is owner, removes
   * user as subscriber from playlists where not owner and deletes all analytics entries
   * for said user.
   *
   * @param userId   - the ID for the user to be deleted
   * @param clientId - the ID for the client from which the user will be deleted
   * @return - if OK, User corresponding to deleted User entry in table; else, BAD_REQUEST
   */

  @DeleteMapping("/deleteUser")
  @ResponseBody
  public ResponseEntity<User> deleteUser(
      @RequestParam(name = "id") String userId,
      @RequestParam(name = "clientId") String clientId) {
    try {
      Long id = Long.parseLong(userId);
      Long clientIdLong = Long.parseLong(clientId);
      User toDelete = client1Service.deleteUser(id, clientIdLong);
      return new ResponseEntity<>(toDelete, HttpStatus.OK);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }
}
