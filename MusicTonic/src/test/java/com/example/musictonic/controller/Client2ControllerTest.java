package com.example.musictonic.controller;


import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.musictonic.utils.PopularSongsReturn;
import com.example.musictonic.model.Song;
import com.example.musictonic.repository.SongRepository;
import com.example.musictonic.repository.UserRepository;
import com.example.musictonic.services.Client1Service;
import com.example.musictonic.services.Client2Service;
import com.example.musictonic.services.Client3Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class Client2ControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private SongRepository songRepo;

  @MockBean
  private Client3Service client3Service;
  @MockBean
  private Client2Service client2Service;

  @MockBean
  private Client1Service client1Service;

  @MockBean
  private UserRepository userRepo;

  private Song song1;
  private Song song2;
  private Song song3;
  private List<Song> top3 = new ArrayList<>();

  private PopularSongsReturn expectedResult;


  @BeforeEach
  void init() {
    song1 = new Song("SongySongyPopPop", 2, "CoolestArtist", "YaYaMerchMakesYou COOL", 10);
    song2 = new Song("CoffeeYolo", 2, "CoolestArtist",
        "Caffeine is like cash both start with a C and end with an E or H", 5);
    song3 = new Song("Sawgeroo", 5, "CoolestArtist", "Valgrind all day and all night", 5);
    top3.add(song1);
    top3.add(song2);
    top3.add(song3);
    Double averagePlaylist = 2.0;
    expectedResult = new PopularSongsReturn(top3, averagePlaylist);

  }

  @Test
  @DisplayName("getMostPopularSongs() WORKS")
  void getMostPopularSongsGood() throws Exception {
    when(client2Service.getMostPopularSongs()).thenReturn(expectedResult);
    mvc.perform(get("/client2-rest/top3songs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(expectedResult)))
        .andExpect(status().isOk())
        .andExpect(
            jsonPath("$.averageNumberPlaylists", is(expectedResult.getAverageNumberPlaylists())));
  }

  @Test
  @DisplayName("getMostPopularSongs() FAILS, as expected")
  void getMostPopularSongsBad() throws Exception {
    mvc.perform(put("/client2-rest/top3songs")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isMethodNotAllowed());
  }

}
