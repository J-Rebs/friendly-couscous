package com.example.musictonic.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.musictonic.Utils.PlaySongReturn;
import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.AnalyticsPlaylist;
import com.example.musictonic.model.AnalyticsSong;
import com.example.musictonic.model.AnalyticsUser;
import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.Song;
import com.example.musictonic.model.User;
import com.example.musictonic.model.UserType;
import com.example.musictonic.repository.UserRepository;
import com.example.musictonic.services.Client1Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class Client1ControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private UserRepository userRepo;

  @MockBean
  private Client1Service client1Service;

  private User user;
  private Song song;
  private Playlist playlist;
  private Analytics a;
  private AnalyticsUser analyticsUser;
  private AnalyticsSong analyticsSong;
  private AnalyticsPlaylist analyticsPlaylist;

  private Timestamp timestamp;
  private PlaySongReturn response;

  @BeforeEach
  void init() {
    user = new User(1L, "Cool Guy", UserType.ARTIST, "country", 27);
    song = new Song(1L, "SongySongyPopPop", 2, "CoolestArtist", "YaYaMerchMakesYou COOL", 10);
    playlist = new Playlist(1L, 1L, "TheBEST", false);

    analyticsUser = new AnalyticsUser(a, user);
    analyticsSong = new AnalyticsSong(a, song);
    analyticsPlaylist = new AnalyticsPlaylist(a, playlist);

    Date date = new Date();
    timestamp = new Timestamp(date.getTime());
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    String timestampString = formatter.format(timestamp.toLocalDateTime());
    a = new Analytics(timestampString);
    response =
        new PlaySongReturn(a.getAnalyticsId(), a.getTimestamp());

  }

  @Test
  @DisplayName("/client1-rest/playsong POST route WORKS, as expected")
  void createAnalyticalSongGood() throws Exception {
    when(client1Service.playSong(any(Long.class), any(Long.class), any(Long.class))).thenReturn(a);
    mvc.perform(post("/client1-rest/playsong?userid=1&songid=1&playlistid=1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(response)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(response.getId())))
        .andExpect(jsonPath("$.timestamp", is(response.getTimestamp())));
  }

  @Test
  @DisplayName("/client1-rest/playsong POST route FAILS, as expected")
  void createAnalyticalSongBad() throws Exception {
    mvc.perform(post("/client1-rest/playsong?userid=1&songid=1&playlistid=1"))
        .andExpect(status().isBadRequest());
  }

}
