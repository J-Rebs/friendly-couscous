package com.example.musictonic.controller;

import com.example.musictonic.Utils.PlaySongReturn;
import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.User;
import com.example.musictonic.repository.UserRepository;
import com.example.musictonic.services.Client1Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.boot.configurationprocessor.json;
//import org.springframework.web.bind.annotation.PostMapping;


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

  @PostMapping("/likeSong")
  @ResponseBody
  public ResponseEntity<Integer> likeSong(
      @RequestParam(name = "userid") String userId,
      @RequestParam(name = "songid") String songId) {
    try {
      Integer likedSongCount =
          client1Service.likeSong(Long.parseLong(userId), Long.parseLong(songId));
      return new ResponseEntity<>(likedSongCount, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }
}
