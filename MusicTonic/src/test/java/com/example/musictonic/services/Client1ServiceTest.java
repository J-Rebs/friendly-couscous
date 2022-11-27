package com.example.musictonic.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.AnalyticsPlaylist;
import com.example.musictonic.model.AnalyticsSong;
import com.example.musictonic.model.AnalyticsUser;
import com.example.musictonic.model.Client;
import com.example.musictonic.model.ClientPlaylist;
import com.example.musictonic.model.ClientSong;
import com.example.musictonic.model.ClientUser;
import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.PlaylistToSubscriber;
import com.example.musictonic.model.Song;
import com.example.musictonic.model.User;
import com.example.musictonic.model.UserType;
import com.example.musictonic.repository.AnalyticsPlaylistRepository;
import com.example.musictonic.repository.AnalyticsRepository;
import com.example.musictonic.repository.AnalyticsSongRepository;
import com.example.musictonic.repository.AnalyticsUserRepository;
import com.example.musictonic.repository.ClientPlaylistRepository;
import com.example.musictonic.repository.ClientRepository;
import com.example.musictonic.repository.ClientSongRepository;
import com.example.musictonic.repository.ClientUserRepository;
import com.example.musictonic.repository.PlaylistRepository;
import com.example.musictonic.repository.PlaylistToSongRepository;
import com.example.musictonic.repository.PlaylistToSubscriberRepository;
import com.example.musictonic.repository.SongRepository;
import com.example.musictonic.repository.UserRepository;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Client1ServiceTest {

  //@Rule
  //public final ExpectedException exception = ExpectedException.none();

  @Mock
  AnalyticsRepository analyticsRepo;

  @Mock
  AnalyticsUserRepository analyticsUserRepo;

  @Mock
  AnalyticsSongRepository analyticsSongRepo;

  @Mock
  AnalyticsPlaylistRepository analyticsPlaylistRepo;

  @Mock
  PlaylistToSubscriberRepository playlistToSubscriberRepo;

  @Mock
  UserRepository userRepo;

  @Mock
  SongRepository songRepo;

  @Mock
  PlaylistRepository playlistRepo;

  @Mock
  AnalyticsUserRepository analyticsUserRepository;

  @Mock
  AnalyticsSongRepository analyticsSongRepository;

  @Mock
  AnalyticsPlaylistRepository analyticsPlaylistRepository;

  @Mock
  PlaylistToSongRepository playlistToSongRepository;

  @Mock
  ClientRepository clientRepo;

  @Mock
  ClientPlaylistRepository clientPlaylistRepo;

  @Mock
  ClientUserRepository clientUserRepo;

  @Mock
  ClientSongRepository clientSongRepo;


  @InjectMocks
  Client1Service client1Service;

  private User user;
  private Song song;
  private Playlist playlist;

  private Client client;

  private ClientUser clientUser;
  private List<ClientUser> clientUserList = new ArrayList<>();

  private ClientSong clientSong;
  private List<ClientSong> clientSongList = new ArrayList<>();

  private ClientPlaylist clientPlaylist;
  private List<ClientPlaylist> clientPlaylistList = new ArrayList<>();

  private Analytics a;

  private List<User> list = new ArrayList<>();

  private AnalyticsUser analyticsUser;
  private AnalyticsSong analyticsSong;
  private AnalyticsPlaylist analyticsPlaylist;

  private Timestamp timestamp;

  @BeforeEach
  void init() {
    user = new User(1L, "Cool Guy", UserType.ARTIST, "country", 27);
    song = new Song(1L, "SongySongyPopPop", 2, "CoolestArtist", "YaYaMerchMakesYou COOL", 10);
    playlist = new Playlist(1L, 1L, "TheBEST", false);
    client = new Client(1L);
    clientUser = new ClientUser(1L, client, user);
    clientUserList.add(clientUser);
    clientSong = new ClientSong(1L, client, song);
    clientSongList.add(clientSong);
    clientPlaylist = new ClientPlaylist(1L, client, playlist);
    clientPlaylistList.add(clientPlaylist);


    list.add(user);

    analyticsUser = new AnalyticsUser(a, user);
    analyticsSong = new AnalyticsSong(a, song);
    analyticsPlaylist = new AnalyticsPlaylist(a, playlist);

    Date date = new Date();
    timestamp = new Timestamp(date.getTime());
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    String timestampString = formatter.format(timestamp.toLocalDateTime());
    a = new Analytics(timestampString);

  }

  @Test
  @DisplayName("playSong() WORKS")
  void playSongGood() throws IllegalAccessException {
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);
    when(songRepo.findBySongId(any(Long.class))).thenReturn(song);
    when(playlistRepo.findByPlaylistId(any(Long.class))).thenReturn(playlist);
    when(analyticsRepo.save(any(Analytics.class))).thenReturn(a);
    when(analyticsUserRepo.save(any(AnalyticsUser.class))).thenReturn(analyticsUser);
    when(analyticsSongRepo.save(any(AnalyticsSong.class))).thenReturn(analyticsSong);
    when(analyticsPlaylistRepo.save(any(AnalyticsPlaylist.class))).thenReturn(analyticsPlaylist);
    when(songRepo.findBySongId(any(Long.class))).thenReturn(song);
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);
    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserList);
    when(clientSongRepo.findAllByClient(any(Client.class))).thenReturn(clientSongList);
    when(clientPlaylistRepo.findAllByClient(any(Client.class))).thenReturn(clientPlaylistList);

    Analytics aReturn = client1Service.playSong(1L, 1L, 1L, 1L);
    assertNotNull(aReturn);
    assertThat(aReturn.getTimestamp()).isEqualTo(a.getTimestamp());
  }

  // Syntax for assertThrows src: https://junit.org/junit5/docs/current/user-guide/#extensions-exception-handling
  @Test
  @DisplayName("playSong() FAILS, as expected")
  void playSongBad() throws IllegalAccessException {
    assertThrows(IllegalAccessException.class, () -> client1Service.playSong(1L, 1L, 1L, 1L));
  }

  @Test
  @DisplayName("likeSong() WORKS")
  void likeSongGood() throws IllegalAccessException {
    when(songRepo.findBySongId(any(Long.class))).thenReturn(song);
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);
    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserList);
    when(clientSongRepo.findAllByClient(any(Client.class))).thenReturn(clientSongList);


    Integer originalSongLikesCount = song.getSongLikesCount();
    for (int i = 0; i < 9; i++) {
      client1Service.likeSong(1L, 1L, 1L);
    }
    Integer songLikesCount = client1Service.likeSong(1L, 1L, 1L);

    assertEquals(originalSongLikesCount + 10, songLikesCount);
  }

  @Test
  @DisplayName("likeSong() FAILS, as expected")
  void likeSongBad() throws IllegalAccessException {
    when(songRepo.findBySongId(any(Long.class))).thenReturn(song);
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);
    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserList);
    when(clientSongRepo.findAllByClient(any(Client.class))).thenReturn(clientSongList);


    Integer originalSongLikesCount = song.getSongLikesCount();
    for (int i = 0; i < 9; i++) {
      client1Service.likeSong(1L, 1L, 1L);
    }
    Integer songLikesCount = client1Service.likeSong(1L, 1L, 1L);

    assertNotEquals(originalSongLikesCount + 8, songLikesCount);
  }

  @Test
  @DisplayName("getAllUsers() WORKS")
  void getAllUsersGood() throws IllegalAccessException {
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserList);
    List<User> returnedList = client1Service.getAllUsers(1L);
    assertEquals(returnedList, list);
  }

  @Test
  @DisplayName("getAllUsers() FAILS, as expected")
  void getAllUsersBad() throws IllegalAccessException {
    List<User> fakeList = new ArrayList<>();
    // when(userRepo.findAll()).thenReturn(fakeList);
    List<User> returnedList = client1Service.getAllUsers(1L);
    assertNotEquals(returnedList, list);
  }

  @Test
  @DisplayName("createUser() WORKS")
  void createUserGood() throws Exception {
    when(userRepo.save(any(User.class))).thenReturn(user);
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    User returnedUser = client1Service.createUser("Cool Guy", UserType.ARTIST, "country", 27, 1L);
    assertEquals(user, returnedUser);
  }

  @Test
  @DisplayName("createUser() FAILS, as expected")
  void createUserBad() throws Exception {
    assertThrows(IllegalAccessException.class,
        () -> client1Service.createUser("Cool Guy", UserType.ARTIST, "country", 27, 1L));
  }

  @Test
  @DisplayName("deleteUser() WORKS")
  void deleteUserGood() throws IllegalAccessException {
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);

    List<AnalyticsUser> list1 = new ArrayList<>();
    when(analyticsUserRepo.findByUser(any(User.class))).thenReturn(list1);

    List<PlaylistToSubscriber> list2 = new ArrayList<>();
    when(playlistToSubscriberRepo.findAllByUser(any(User.class))).thenReturn(list2);

    List<Playlist> list3 = new ArrayList<>();
    when(playlistRepo.findAllByOwner(any(Long.class))).thenReturn(list3);

    client1Service.deleteUser(1L, 1L);
    verify(userRepo, times(1)).delete(user);
  }

  @Test
  @DisplayName("deleteUser() FAILS, as expected")
  void deleteUserBad() throws ObjectNotFoundException {
    when(userRepo.findByUserId(any(Long.class))).thenReturn(null);

    User user1 = client1Service.deleteUser(1L, 1L);
    assertNotEquals(user1, user);
  }

}
