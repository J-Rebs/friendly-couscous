package com.example.musictonic.services;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
import com.example.musictonic.model.PlaylistToSongs;
import com.example.musictonic.model.Song;
import com.example.musictonic.model.User;
import com.example.musictonic.model.UserType;
import com.example.musictonic.repository.AnalyticsPlaylistRepository;
import com.example.musictonic.repository.AnalyticsRepository;
import com.example.musictonic.repository.AnalyticsSongRepository;
import com.example.musictonic.repository.AnalyticsUserRepository;
import com.example.musictonic.repository.ClientRepository;
import com.example.musictonic.repository.ClientUserRepository;
import com.example.musictonic.repository.PlaylistRepository;
import com.example.musictonic.repository.PlaylistToSongRepository;
import com.example.musictonic.repository.SongRepository;
import com.example.musictonic.repository.UserRepository;
import com.example.musictonic.utils.AnalyticsInfoBasic;
import com.example.musictonic.utils.PopularSongsReturn;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
public class Client3ServiceTest {

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

  @InjectMocks
  Client3Service client3Service;


  private Client client;

  private ClientUser clientUser;

  private List<ClientUser> clientUserList = new ArrayList<>();
  private User user;
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
    clientUser = new ClientUser(client, user);
    clientUserList.add(clientUser);

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
  void getUserInformationBad() throws IllegalAccessException {
    when(clientRepo.findByClientId(any(Long.class))).thenReturn(client);
    when(userRepo.findByUserId(any(Long.class))).thenReturn(user);
    List<ClientUser> clientUserListBad = new ArrayList<>();
    when(clientUserRepo.findAllByClient(any(Client.class))).thenReturn(clientUserListBad);
    assertThrows(IllegalAccessException.class, () -> client3Service.getUserInformation(1L, 1L));

  }


}
