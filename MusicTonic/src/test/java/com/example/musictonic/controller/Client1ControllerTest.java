package com.example.musictonic.controller;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.musictonic.jwt.JwtController;
import com.example.musictonic.jwt.JwtService;
import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.AnalyticsPlaylist;
import com.example.musictonic.model.AnalyticsSong;
import com.example.musictonic.model.AnalyticsUser;
import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.Song;
import com.example.musictonic.model.User;
import com.example.musictonic.model.UserType;
import com.example.musictonic.repository.SongRepository;
import com.example.musictonic.repository.UserRepository;
import com.example.musictonic.services.Client1Service;
import com.example.musictonic.services.Client2Service;
import com.example.musictonic.services.Client3Service;
import com.example.musictonic.utils.PlaySongReturn;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.NestedServletException;

/***
 * Our testing framework is based on: https://github.com/scbushan05/spring-boot-jpa-unit-testing
 *                 The tutorial link: https://youtu.be/pHTr3IMuRh0
 */


@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
class Client1ControllerTest {

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
  private User user2;

  private User user3;

  private List<User> listOfUsers;

  private Song song;
  private Playlist playlist;
  private Analytics a;
  private AnalyticsUser analyticsUser;
  private AnalyticsSong analyticsSong;
  private AnalyticsPlaylist analyticsPlaylist;

  private Timestamp timestamp;
  private DateTimeFormatter formatter;
  private PlaySongReturn response;

  @BeforeEach
  void init() {
    user = new User(1L, "Cool Guy", UserType.ARTIST, "country", 27);
    user2 = new User(1L, "John Bobstin", UserType.ARTIST, "kpop", 12);
    user3 = new User(1L, "Bobstin Johnstin", UserType.LISTENER, "metal", 94);
    listOfUsers = new ArrayList<>();
    listOfUsers.add(user);
    listOfUsers.add(user2);
    listOfUsers.add(user3);

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
  @DisplayName("/client1-rest/playsong POST route WORKS")
  void createAnalyticalSongGood() throws Exception {
    when(client1Service.playSong(any(Long.class), any(Long.class), any(Long.class),
        any(Long.class))).thenReturn(a);
    mvc.perform(post("/client1-rest/playsong?userid=1&songid=1&playlistid=1&clientid=1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(response)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(response.getId())));
  }

  @Test
  @DisplayName("/client1-rest/playsong POST route FAILS, as expected")
  void createAnalyticalSongBad() throws NestedServletException {
    assertThrows(NestedServletException.class, () -> mvc.perform(
        post("/client1-rest/playsong?userid=1&songid=1&playlistid=1&clientid=1")));
  }

  @Test
  @DisplayName("/client1-rest/likesong PUT route WORKS")
  void likeSongGood() throws Exception {
    Integer check = song.getSongLikesCount() + 1;
    when(client1Service.likeSong(any(Long.class), any(Long.class), any(Long.class))).thenReturn(
        song.getSongLikesCount() + 1);
    mvc.perform(put("/client1-rest/likeSong?userid=1&songid=1&clientid=1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", is(check)));
  }

  //  @Test
//  @DisplayName("/client1-rest/likesong PUT route FAILS, as expected")
//  void likeSongBad() throws Exception {
//    IllegalArgumentException exception = new IllegalArgumentException();
//    when(client1Service.likeSong(any(Long.class), any(Long.class), any(Long.class))).thenReturn(
//        song.getSongLikesCount() + 1);
//    ResultActions result = mvc.perform(put("/client1-rest/likeSong?userid=1&songid=100")
//            .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isBadRequest());
//  }
  @Test
  @DisplayName("/client1-rest/deleteUser DELETE route WORKS")
  void deleteUserGood() throws Exception {
    when(client1Service.deleteUser(any(Long.class), any(Long.class))).thenReturn(user);
    mvc.perform(delete("/client1-rest/deleteUser?id=1&clientId=1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("/client1-rest/createUser POST route WORKS")
  void createUserGood() throws Exception {
    when(client1Service.createUser(any(String.class), any(UserType.class), any(String.class), any(
        Integer.class), any(Long.class))).thenReturn(user);
    mvc.perform(
            post(
                "/client1-rest/createUser?realname=johnbobstin&usertype=LISTENER&maingenre=metal&age=38&clientid=38")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("/client1-rest/listUsers GET route WORKS")
  void listUsersGood() throws Exception {
    when(client1Service.getAllUsers(any(Long.class))).thenReturn(listOfUsers);
    mvc.perform(
            get(
                "/client1-rest/listUsers?clientid=1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("/client1-rest/likesong runtime")
  void likeSongRuntimeException() throws Exception {
    Integer check = song.getSongLikesCount() + 1;
    when(client1Service.likeSong(any(Long.class), any(Long.class), any(Long.class))).thenThrow(
        RuntimeException.class);
    assertThrows(NestedServletException.class,
        () -> mvc.perform(put("/client1-rest/likeSong?userid=1&songid=1&clientid=1")));
  }


}
