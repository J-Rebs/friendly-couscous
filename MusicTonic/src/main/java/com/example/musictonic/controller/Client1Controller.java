package com.example.musictonic.controller;

import com.example.musictonic.Utils.PlaySongReturn;
import com.example.musictonic.model.Analytics;
import com.example.musictonic.services.Client1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.musictonic.exceptions.BadRequestException;


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


    @GetMapping(value = "/playsong")
    String playSong() {
        return "PLACEHOLDER FOR GET";
    }


    // reference: https://www.baeldung.com/spring-request-param
    @PostMapping("/playsong")
    @ResponseBody
    public ResponseEntity<PlaySongReturn> createAnalyticalSongs(@RequestParam(name = "userid") String userId,
                                                                @RequestParam(name = "songid") String songId,
                                                                @RequestParam(name = "playlistid") String playlistId) {
        try {
            Analytics analytics = client1Service.playSong(Long.parseLong(userId), Long.parseLong(songId), Long.parseLong(playlistId));
            PlaySongReturn response = new PlaySongReturn(analytics.getAnalyticsId(), analytics.getTimestamp());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new BadRequestException("Bad request");
        }
    }
}
