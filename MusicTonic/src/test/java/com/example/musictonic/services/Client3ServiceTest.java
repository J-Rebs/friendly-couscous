package com.example.musictonic.services;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.AnalyticsPlaylist;
import com.example.musictonic.model.AnalyticsSong;
import com.example.musictonic.model.AnalyticsUser;
import com.example.musictonic.model.Client;
import com.example.musictonic.model.ClientUser;
import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.Song;
import com.example.musictonic.model.User;
import com.example.musictonic.model.UserType;
import com.example.musictonic.repository.AnalyticsRepository;
import com.example.musictonic.repository.AnalyticsUserRepository;
import com.example.musictonic.repository.ClientRepository;
import com.example.musictonic.repository.ClientUserRepository;
import com.example.musictonic.repository.PlaylistRepository;
import com.example.musictonic.repository.UserRepository;
import com.example.musictonic.utils.AnalyticsInfoBasic;
import com.example.musictonic.utils.UserExportReturn;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Client3ServiceTest {

  @InjectMocks
  Client3Service client3Service;
  @Mock
  private ClientRepository clientRepo;
  @Mock
  private PlaylistRepository playlistRepo;
  @Mock
  private AnalyticsRepository analyticsRepo;
  @Mock
  private ClientUserRepository clientUserRepo;
  @Mock
  private AnalyticsUserRepository analyticsUserRepo;
  @Mock
  private Client2Service client2Service;
  @Mock
  private Client1Service client1Service;
  @Mock
  private UserRepository userRepo;
  private Client client;
  private Client client2;

  private ClientUser clientUser;
  private ClientUser client2User;
  private ClientUser clientUser2;
  private ClientUser client2User2;

  private List<ClientUser> clientUserList = new ArrayList<>();
  private List<ClientUser> clientUserList2 = new ArrayList<>();
  private List<ClientUser> clientUserList3 = new ArrayList<>();
  private User user;
  private User user2;
  private List<Playlist> playlistList;
  private List<AnalyticsUser> analyticsUserList = new ArrayList<>();
  private List<AnalyticsInfoBasic> analyticsInfoBasicList;

  private Analytics a;
  private List<Analytics> analyticsList = new ArrayList<>();
  private AnalyticsUser analyticsUser;

  private AnalyticsSong analyticsSong;
  private AnalyticsPlaylist analyticsPlaylist;

  private Song song;
  private Timestamp timestamp;
  private UserExportReturn userExportReturn;


  @BeforeEach
  void init() {
    user = new User(1L, "Not so cool Guy", UserType.LISTENER, "heavy metal", 84);
    user2 = new User(2L, "Not so cool Guy", UserType.LISTENER, "heavy metal", 84);
    song = new Song(1L, "SongySongyPopPop", 2, "CoolestArtist", "YaYaMerchMakesYou COOL", 10);

    playlistList = new ArrayList<>();
    Playlist playlist1 = new Playlist(1L, 1L, "a dumb playlist", false);
    Playlist playlist2 = new Playlist(2L, 1L, "my beautiful playlist", false);
    playlistList.add(playlist1);
    playlistList.add(playlist2);

    analyticsInfoBasicList = new ArrayList<>();
    Date date = new Date();
    timestamp = new Timestamp(date.getTime());
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    String timestampString = formatter.format(timestamp.toLocalDateTime());
    AnalyticsInfoBasic analyticsInfoBasic = new AnalyticsInfoBasic(1L, timestampString, user);
    analyticsInfoBasicList.add(analyticsInfoBasic);

    analyticsSong = new AnalyticsSong(a, song);
    analyticsUser = new AnalyticsUser(a, user);
    analyticsPlaylist = new AnalyticsPlaylist(a, playlist1);
    a = new Analytics(1L, timestampString, analyticsSong, analyticsPlaylist, analyticsUser, null);
    analyticsList.add(a);


    analyticsUser = new AnalyticsUser(a, user);
    analyticsUserList.add(analyticsUser);

    client = new Client(1L);
    client2 = new Client(2L);
    clientUser = new ClientUser(1L, client, user);
    clientUser2 = new ClientUser(2L, client, user2);
    client2User2 = new ClientUser(3L, client2, user2);
    client2User = new ClientUser(4L, client2, user);
    clientUserList.add(clientUser);
    clientUserList.add(clientUser2);
    clientUserList.add(client2User2);

    clientUserList2.add(client2User2);
    clientUserList3.add(client2User);


    userExportReturn = new UserExportReturn(user, playlistList, analyticsInfoBasicList);
  }

  @Test
  @DisplayName("getUserInformation() WORKS")
  void getUserInformationGood() throws IllegalAccessException {
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);

    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserList);
    when(playlistRepo.findAllByOwner(any(Long.class))).thenReturn(playlistList);
    when(analyticsUserRepo.findByUser(any(User.class))).thenReturn(analyticsUserList);
    when(analyticsRepo.findByAnalyticsUser(any(AnalyticsUser.class))).thenReturn(a);
    UserExportReturn res =
        client3Service.getUserInformation(1L, 1L);

    assertNotNull(res);
    assertThat(res.getUser()).isEqualTo(user);
    assertThat(res.getAnalyticsList().size()).isEqualTo(analyticsInfoBasicList.size());
    assertThat(res.getPlaylistList().size()).isEqualTo(playlistList.size());

  }

  @Test
  @DisplayName("getUserInformation() FAILS, as expected")
  void getUserInformationBad1() throws IllegalAccessException {
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);
    List<ClientUser> clientUserListBad = new ArrayList<>();
    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserListBad);
    assertThrows(IllegalAccessException.class, () -> client3Service.getUserInformation(1L, 1L));

  }

  @Test
  @DisplayName("getUserInformation() FAILS because clientId is not equal, as expected")
  void getUserInformationBad2() throws IllegalAccessException {
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);
    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserList2);
    assertThrows(IllegalAccessException.class, () -> client3Service.getUserInformation(1L, 1L));
  }

  @Test
  @DisplayName("getUserInformation() FAILS because clientId is not equal, as expected")
  void getUserInformationBad3() throws IllegalAccessException {
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);
    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserList3);
    assertThrows(IllegalAccessException.class, () -> client3Service.getUserInformation(1L, 1L));
  }

}
