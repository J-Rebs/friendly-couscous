package com.example.musictonic.controller;


import com.example.musictonic.services.Client2Service;
import com.example.musictonic.utils.PopularSongsReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Request handler for client type 2 (e.g., aggregated data metrics).
 */
@RestController
@RequestMapping("client2-rest")
public class Client2Controller {

  @Autowired
  Client2Service client2Service;

  /**
   * Function to get the top three most popular songs.
   *
   * @return if OK, ArrayList of top three most popular songs else BAD_REQUEST.
   */
  @GetMapping("/top3songs")
  public ResponseEntity<PopularSongsReturn> getTopThreeSongs() {
    try {
      PopularSongsReturn response = client2Service.getMostPopularSongs();
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }
}
