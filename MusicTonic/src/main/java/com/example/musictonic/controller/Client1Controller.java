package com.example.musictonic.controller;

import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.Song;
import com.example.musictonic.services.Client1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

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


//    private final HttpServletRequest request;

    // Default constructor for Client1Controller
//    public Client1Controller(HttpServletRequest request) { this.request = request; }

    //    @PostMapping(value = "/playsong")
    @GetMapping(value = "/playsong")
    String playSong(){
        return "PLACEHOLDER FOR GET";
    }

    @PostMapping("/playsong/userid")
    @ResponseBody
    public ResponseEntity<Analytics> createAnalyticalSongs(@RequestParam(name = "userid") String userID) {
        try {
            Analytics analytics = client1Service.playSong(Long.parseLong(userID));
            return new ResponseEntity<>(analytics, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/playsong/id")
    @ResponseBody
    public String createSongs(@RequestParam(name = "userid") String userID, @RequestParam String name) {
        return "ID: " + userID + " Name: " + name;
    }
}
