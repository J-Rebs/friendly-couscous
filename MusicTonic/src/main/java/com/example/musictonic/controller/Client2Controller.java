package com.example.musictonic.controller;


import com.example.musictonic.Utils.PlaySongReturn;
import com.example.musictonic.Utils.PopularSongsReturn;
import com.example.musictonic.model.Analytics;
import com.example.musictonic.services.Client2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client2-rest")
public class Client2Controller {

  @Autowired
  Client2Service client2Service;

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
