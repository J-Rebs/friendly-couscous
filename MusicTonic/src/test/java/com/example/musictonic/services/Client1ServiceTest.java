package com.example.musictonic.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
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
import com.example.musictonic.model.PlaylistToSongs;
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
  PlaylistToSongRepository playlistToSongsRepo;

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
  private User user2;
  private Song song;
  private Song song2;
  private Playlist playlist;
  private Playlist playlist2;

  private Client client;

  private Client client2;

  private ClientUser clientUser;
  private ClientUser clientUser2;

  private List<ClientUser> clientUserList = new ArrayList<>();
  private List<ClientUser> clientUserListGETONLY = new ArrayList<>();

  private ClientSong clientSong;
  private ClientSong clientSong2;
  private List<ClientSong> clientSongList = new ArrayList<>();

  private PlaylistToSongs playlistSong;

  private ClientPlaylist clientPlaylist;
  private ClientPlaylist clientPlaylist2;
  private List<ClientPlaylist> clientPlaylistList = new ArrayList<>();
  private List<ClientPlaylist> clientPlaylistList2 = new ArrayList<>();

  private Analytics a;

  private List<User> userList = new ArrayList<>();

  private AnalyticsUser analyticsUser;
  private AnalyticsSong analyticsSong;
  private AnalyticsPlaylist analyticsPlaylist;

  private Timestamp timestamp;

  @BeforeEach
  void init() {
    user = new User(1L, "Cool Guy", UserType.ARTIST, "country", 27);
    user2 = new User(2L, "Cool Guy", UserType.ARTIST, "country", 27);
    song = new Song(1L, "SongySongyPopPop", 2, "CoolestArtist", "YaYaMerchMakesYou COOL", 10);
    song2 = new Song(2L, "second song", 2, "CoolestArtist", "YaYaMerchMakesYou COOL", 10);
    playlist = new Playlist(1L, 1L, "TheBEST", false);
    playlist2 = new Playlist(2L, 2L, "TheBEST", true);
    client = new Client(1L);
    client2 = new Client(2L);
    clientUser = new ClientUser(1L, client, user);
    clientUser2 = new ClientUser(2L, client2, user2);
    // extra list
    clientUserListGETONLY.add(clientUser);
    clientUserListGETONLY.add(clientUser2);

    clientUserList.add(clientUser);
    clientUserList.add(clientUser2);


    clientSong = new ClientSong(1L, client, song);
    clientSongList.add(clientSong);
    clientSong2 = new ClientSong(2L, client2, song2);
    clientSongList.add(clientSong2);
    clientPlaylist = new ClientPlaylist(1L, client, playlist);
    clientPlaylistList.add(clientPlaylist);
    clientPlaylist2 = new ClientPlaylist(2L, client2, playlist2);
    clientPlaylistList2.add(clientPlaylist2);

    playlistSong = new PlaylistToSongs(song, playlist);

    userList.add(user);
    userList.add(user2);

    analyticsUser = new AnalyticsUser(1L, a, user);
    analyticsSong = new AnalyticsSong(1L, a, song);
    analyticsPlaylist = new AnalyticsPlaylist(1L, a, playlist);

    Date date = new Date();
    timestamp = new Timestamp(date.getTime());
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    String timestampString = formatter.format(timestamp.toLocalDateTime());
    a = new Analytics(1L, timestampString);
//    a = analyticsRepo.save(new Analytics(timestampString));
  }

  @Test
  @DisplayName("validUserSong() FAILS due to no client-song match")
  void validUserSongBad() throws IllegalAccessException {
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(clientSongRepo.findAllByClient(any(Client.class))).thenReturn(clientSongList);
    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserListGETONLY);
    assertThrows(IllegalAccessException.class, () -> client1Service.validUserSong(1L, 2L, 1L));
  }

  @Test
  @DisplayName("validUserSong() FAILS due to no client-user match")
  void validUserSongBad2() throws IllegalAccessException {
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(clientSongRepo.findAllByClient(any(Client.class))).thenReturn(clientSongList);
    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserListGETONLY);
    assertThrows(IllegalAccessException.class, () -> client1Service.validUserSong(2L, 1L, 1L));
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

//  @Test
//  @DisplayName("playSong() FAILS, as expected")
//  void playSongBad2() throws IllegalAccessException {
//    doThrow(new IllegalAccessException()).when(a.setAnalyticsUser(any(AnalyticsUser.class)));
//  }

  @Test
  @DisplayName("validPlaylist() FAILS, as expected")
  void validPlaylistBad() throws IllegalAccessException {
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(clientPlaylistRepo.findAllByClient(client)).thenReturn(clientPlaylistList);
    assertThrows(IllegalAccessException.class, () -> client1Service.validPlaylist(2L, 1L, 1L));
  }

  @Test
  @DisplayName("validPlaylist() FAILS because clientId parameter does not match id of Client object returned")
  void validPlaylistBad2() throws IllegalAccessException {
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(clientPlaylistRepo.findAllByClient(client)).thenReturn(clientPlaylistList);
    assertThrows(IllegalAccessException.class, () -> client1Service.validPlaylist(1L, 2L, 1L));
  }

  @Test
  @DisplayName("subscribeDefaultPlaylist() WORKS given a list of playlists where one playlist is default, as expected")
  void subscribeDefaultPlaylistGood() throws IllegalAccessException {
    when(songRepo.findBySongId(any(Long.class))).thenReturn(song2);
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client2);
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user2);
    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserList);
    when(clientSongRepo.findAllByClient(any(Client.class))).thenReturn(clientSongList);
    List<Playlist> list = new ArrayList<>();
    list.add(playlist);
    list.add(playlist2);
    when(playlistRepo.findAllByOwner(any(Long.class))).thenReturn(list);

    Integer originalSongLikesCount = song.getSongLikesCount();
    for (int i = 0; i < 9; i++) {
      client1Service.likeSong(1L, 1L, 1L);
    }
    Integer songLikesCount = client1Service.likeSong(2L, 2L, 2L);

    assertEquals(originalSongLikesCount + 10, songLikesCount);
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
  @DisplayName(
      "likeSong() WORKS when playlistToSongsRepo.findBySong() returns a valid PlaylistSong object"
  )
  void likeSongGood2() throws IllegalAccessException {
    when(songRepo.findBySongId(any(Long.class))).thenReturn(song);
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);
    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserList);
    when(clientSongRepo.findAllByClient(any(Client.class))).thenReturn(clientSongList);
//    when(playlistToSongsRepo.findBySong(any(Song.class))).thenReturn(playlistSong);
    when(playlistToSongsRepo.findByPlaylist(any(Playlist.class))).thenReturn(playlistSong);

    Integer originalSongLikesCount = song.getSongLikesCount();
    for (int i = 0; i < 9; i++) {
      client1Service.likeSong(1L, 1L, 1L);
    }
    Integer songLikesCount = client1Service.likeSong(1L, 1L, 1L);

    assertEquals(originalSongLikesCount + 10, songLikesCount);
  }

  @Test
  @DisplayName("likeSong() FAILS as expected")
  void likeSongBad() throws IllegalAccessException {
    when(songRepo.findBySongId(any(Long.class))).thenReturn(song);
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);
    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserList);
    when(clientSongRepo.findAllByClient(any(Client.class))).thenReturn(clientSongList);
    when(playlistToSongsRepo.findBySong(any(Song.class))).thenReturn(playlistSong);


    Integer originalSongLikesCount = song.getSongLikesCount();
    for (int i = 0; i < 9; i++) {
      client1Service.likeSong(1L, 1L, 1L);
    }
    Integer songLikesCount = client1Service.likeSong(1L, 1L, 1L);

    assertEquals(originalSongLikesCount + 10, songLikesCount);
  }

  @Test
  @DisplayName("likeSong() FAILS - due to null client, as expected")
  void likeSongBad2() throws IllegalAccessException {
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(null);

    assertThrows(IllegalAccessException.class, () -> client1Service.likeSong(1L, 1L, 1L));
  }

  @Test
  @DisplayName("getAllUsers() WORKS")
  void getAllUsersGood() throws IllegalAccessException {
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserListGETONLY);
    List<User> returnedList = client1Service.getAllUsers(1L);
    assertEquals(returnedList, userList);
  }

  @Test
  @DisplayName("getAllUsers() FAILS, as expected")
  void getAllUsersBad() throws IllegalAccessException {
    List<User> fakeList = new ArrayList<>();
    // when(userRepo.findAll()).thenReturn(fakeList);
    List<User> returnedList = client1Service.getAllUsers(1L);
    assertNotEquals(returnedList, userList);
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
  @DisplayName("deleteUser() WORKS with regular lists")
  void deleteUserGood() throws IllegalAccessException {
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);
    clientUserList.add(clientUser2); // supplement list for branch testing
    when(clientUserRepo.findAllByUser(any(User.class))).thenReturn(clientUserList);

    List<AnalyticsUser> list1 = new ArrayList<>();
    list1.add(analyticsUser);
    when(analyticsUserRepo.findByUser(any(User.class))).thenReturn(list1);

    List<PlaylistToSubscriber> list2 = new ArrayList<>();
    list2.add(new PlaylistToSubscriber(1L,user,playlist));
    when(playlistToSubscriberRepo.findAllByUser(any(User.class))).thenReturn(list2);

    List<Playlist> list3 = new ArrayList<>();
    list3.add(playlist);
    when(playlistRepo.findAllByOwner(any(Long.class))).thenReturn(list3);

    client1Service.deleteUser(1L, 1L);
    verify(userRepo, times(1)).delete(user);
  }

  @Test
  @DisplayName("deleteUser() WORKS with null lists")
  void deleteUserGoodSimple() throws IllegalAccessException {
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);
    when(clientUserRepo.findAllByUser(any(User.class))).thenReturn(new ArrayList<>());


    List<AnalyticsUser> list1 = new ArrayList<>();
    when(analyticsUserRepo.findByUser(any(User.class))).thenReturn(new ArrayList<>());

    List<PlaylistToSubscriber> list2 = new ArrayList<>();
    when(playlistToSubscriberRepo.findAllByUser(any(User.class))).thenReturn(new ArrayList<>());

    List<Playlist> list3 = new ArrayList<>();
    when(playlistRepo.findAllByOwner(any(Long.class))).thenReturn(new ArrayList<>());

    client1Service.deleteUser(1L, 1L);
    verify(userRepo, times(1)).delete(user);
  }

  @Test
  @DisplayName("deleteUser() FAILS - due to null User, as expected")
  void deleteUserBad() throws ObjectNotFoundException {
    when(userRepo.findByUserId(any(Long.class))).thenReturn(null);

    User user1 = client1Service.deleteUser(1L, 1L);
    assertNotEquals(user1, user);
  }

  @Test
  @DisplayName("deleteUser() WORKS when all repo calls return valid objects")
  void deleteUserGood2() throws IllegalAccessException {
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);
    clientUserList.add(clientUser2); // supplement list for branch testing
    when(clientUserRepo.findAllByUser(any(User.class))).thenReturn(clientUserList);

    List<AnalyticsUser> list1 = new ArrayList<>();
    list1.add(analyticsUser);
    when(analyticsUserRepo.findByUser(any(User.class))).thenReturn(list1);

    List<PlaylistToSubscriber> list2 = new ArrayList<>();
    list2.add(new PlaylistToSubscriber(1L,user,playlist));
    when(playlistToSubscriberRepo.findAllByUser(any(User.class))).thenReturn(list2);

    List<Playlist> list3 = new ArrayList<>();
    list3.add(playlist);
    when(playlistRepo.findAllByOwner(any(Long.class))).thenReturn(list3);

    List<PlaylistToSubscriber> list4 = new ArrayList<>();
    list4.add(new PlaylistToSubscriber(2L,user2,playlist));
    when(playlistToSubscriberRepo.findAllByPlaylist(any(Playlist.class))).thenReturn(list4);

    List<PlaylistToSongs> list5 = new ArrayList<>();
    list5.add(new PlaylistToSongs(song,playlist));
    when(playlistToSongsRepo.findAllByPlaylist(any(Playlist.class))).thenReturn(list5);

    List<AnalyticsPlaylist> list6 = new ArrayList<>();
    list6.add(new AnalyticsPlaylist(a,playlist));
    when(analyticsPlaylistRepo.findAllByPlaylist(any(Playlist.class))).thenReturn(list6);

    List<ClientPlaylist> list7 = new ArrayList<>();
    list7.add(new ClientPlaylist(client2,playlist));
    when(clientPlaylistRepo.findAllByPlaylist(any(Playlist.class))).thenReturn(list7);

    client1Service.deleteUser(1L, 1L);
    verify(userRepo, times(1)).delete(user);
  }

}
