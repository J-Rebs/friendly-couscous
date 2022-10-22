package com.example.musictonic.controller;

import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.Song;
import com.example.musictonic.services.Client1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//    List<Long> playSong() {
//        List<Analytics> l = client1Service.playSong(1,1,1);
//        List<Long> result = new ArrayList<>();
//
//        for (Analytics a : l) {
//            result.add(a.getAnalyticsId());
//        }
//
//        return result;
//    }
    List<Long> playSong() {
        List<Analytics> l = client1Service.playSong(1,1,1);
        List<Long> result = new ArrayList<>();

        for (Analytics a : l) {
            result.add(a.getAnalyticsId());
        }

        return result;
    }
}
