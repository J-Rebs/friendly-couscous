package com.example.musictonic.controller;


import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.musictonic.jwt.JwtController;
import com.example.musictonic.jwt.JwtService;
import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.Song;
import com.example.musictonic.model.User;
import com.example.musictonic.model.UserType;
import com.example.musictonic.repository.SongRepository;
import com.example.musictonic.repository.UserRepository;
import com.example.musictonic.services.Client1Service;
import com.example.musictonic.services.Client2Service;
import com.example.musictonic.services.Client3Service;
import com.example.musictonic.utils.AnalyticsInfoBasic;
import com.example.musictonic.utils.UserExportReturn;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public class Client3ControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private JwtController userController;

  @MockBean
  private JwtService userService;

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

  private User user;
  private List<Playlist> playlistList;

  private Song song;
  private Song song2;
  private Song song3;
  private List<Song> listOfSongs;

  private List<AnalyticsInfoBasic> analyticsList;

  private Timestamp timestamp;
  private UserExportReturn userExportReturn;


  @BeforeEach
  void init() {
    user = new User(1L, "Not so cool Guy", UserType.LISTENER, "heavy metal", 84);

    song = new Song("pathetique sonata", 20, "Beethoven", "n/a", 3000);
    song2 = new Song("waldstein sonata", 20, "Beethoven", "n/a", 2000);
    song3 = new Song("appassionata sonata", 25, "Beethoven", "n/a", 1000);

    listOfSongs = new ArrayList<>();
    listOfSongs.add(song);
    listOfSongs.add(song2);
    listOfSongs.add(song3);

    playlistList = new ArrayList<>();
    Playlist playlist1 = new Playlist(1L, 1L, "a dumb playlist", false);
    Playlist playlist2 = new Playlist(2L, 1L, "my beautiful playlist", false);
    playlistList.add(playlist1);
    playlistList.add(playlist2);

    analyticsList = new ArrayList<>();
    Date date = new Date();
    timestamp = new Timestamp(date.getTime());
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    String timestampString = formatter.format(timestamp.toLocalDateTime());
    AnalyticsInfoBasic analyticsInfoBasic = new AnalyticsInfoBasic(1L, timestampString, user);
    analyticsList.add(analyticsInfoBasic);

    userExportReturn = new UserExportReturn(user, playlistList, analyticsList);
  }

  @Test
  @DisplayName("getUserInformation() WORKS")
  void getUserInformationGood() throws Exception {
    when(client3Service.getUserInformation(any(Long.class), any(Long.class))).thenReturn(
        userExportReturn);
    mvc.perform(get("/client3-rest/userexport?userid=1&clientid=1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userExportReturn)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.user.userId", is(1)));
  }

  @Test
  @DisplayName("getUserInformation() route FAILS, as expected")
  void getUserInformationBad() throws Exception {
    when(client3Service.getUserInformation(any(Long.class), any(Long.class))).thenReturn(
        userExportReturn);
    mvc.perform(get("/client3-rest/userexport?songid=1"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("listSongs() route WORKS, as expected")
  void listSongsGood() throws Exception {
    when(songRepo.findAll()).thenReturn(listOfSongs);
    mvc.perform(get("/client3-rest/listSongs"))
        .andExpect(status().isOk());
  }


}
