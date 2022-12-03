package com.example.musictonic.controller;

import com.example.musictonic.model.Song;
import com.example.musictonic.repository.SongRepository;
import com.example.musictonic.services.Client3Service;
import com.example.musictonic.utils.UserExportReturn;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Request handler for client type 3 (e.g., raw data dump).
 */
@CrossOrigin
@RestController
@RequestMapping("client3-rest")
public class Client3Controller {
  @Autowired
  Client3Service client3Service;

  @Autowired
  SongRepository songRepo;

  /**
   * Method to get and return user (i.e., type 1 client) information.
   *
   * @param userId   - the unique ID for this user
   * @param clientId - the ID for the client in question
   * @return the analytics history for this client
   */
  @GetMapping("/userexport")
  @ResponseBody
  public ResponseEntity<UserExportReturn> getUserInformation(
      @RequestParam(name = "userid") String userId,
      @RequestParam(name = "clientid") String clientId) {
    try {
      UserExportReturn response =
          client3Service.getUserInformation(Long.parseLong(userId), Long.parseLong(clientId));
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/listSongs")
  List<Song> listSongs() {
    List<Song> l = new ArrayList<>();
    for (Song s : songRepo.findAll()) {
      l.add(s);
    }
    return l;
  }
}
