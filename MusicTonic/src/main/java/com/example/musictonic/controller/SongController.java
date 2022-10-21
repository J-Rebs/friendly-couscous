package com.example.musictonic.controller;

import com.example.musictonic.model.Song;
import com.example.musictonic.repository.SongRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * "To wrap your repository with a web layer, you must turn to Spring MVC.
 * Thanks to Spring Boot, there is little in infrastructure to code. Instead, we can focus on actions[.]"
 * SRC: https://spring.io/guides/tutorials/rest/
 */
@RestController
// " indicates that the data returned by each method will be written straight into the response body instead of rendering a template. " (Same src as above)
public class SongController {

    private final SongRepository repository;

    public SongController(SongRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/songartists")
    List<String> findSongArtists() {
        List<String> l = new ArrayList<String>();

        for (Song u : repository.findAll()) {
            l.add(u.getSongArtist());
        }

        return l;
    }

}
