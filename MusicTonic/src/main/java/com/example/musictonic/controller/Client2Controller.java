package com.example.musictonic.controller;


import com.example.musictonic.model.User;
import com.example.musictonic.services.Client2Service;
import com.example.musictonic.utils.PopularSongsReturn;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Request handler for client type 2 (e.g., aggregated data metrics).
 */
@CrossOrigin
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

  @GetMapping("/numberOfUsersInAgeRange")
  public ResponseEntity<Integer> numberOfUsersInAgeRange(
      @RequestParam(name = "from") Integer fromAge,
      @RequestParam(name = "to") Integer toAge) {
    try {
      Integer averageSongDuration = client2Service.getNumberOfUsersInAgeRange(fromAge, toAge);
      return new ResponseEntity<>(averageSongDuration, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * TODO: (ideas to possibly implement)
   *       - average song duration for listeners in an age range
   *       - average number of songs played per day per user
   *       - average song duration for a given artist
   *       - total number of registered listeners and registered artists
   */
}
