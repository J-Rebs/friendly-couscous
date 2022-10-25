package com.example.musictonic.services;

import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.Song;
import com.example.musictonic.model.User;
import com.example.musictonic.model.UserType;
import com.example.musictonic.repository.AnalyticsPlaylistRepository;
import com.example.musictonic.repository.AnalyticsRepository;
import com.example.musictonic.repository.AnalyticsSongRepository;
import com.example.musictonic.repository.AnalyticsUserRepository;
import com.example.musictonic.repository.PlaylistRepository;
import com.example.musictonic.repository.SongRepository;
import com.example.musictonic.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class Client1ServiceTest {

  @Autowired
  Client1Service client1Service;

  @MockBean
  AnalyticsRepository analyticsRepo;

  @MockBean
  AnalyticsUserRepository analyticsUserRepo;

  @MockBean
  AnalyticsSongRepository analyticsSongRepo;

  @MockBean
  AnalyticsPlaylistRepository analyticsPlaylistRepo;

  @MockBean
  UserRepository userRepo;

  @MockBean
  SongRepository songRepo;

  @MockBean
  PlaylistRepository playlistRepo;

  @Test
  void playSongBad() {
    User user = new User(1L, "Cool Guy", UserType.ARTIST, "country", 27);
    Song song = new Song(1L, "SongySongyPopPop", 2, "CoolestArtist", "YaYaMerchMakesYou COOL", 10);
    Playlist playlist = new Playlist(1L, "CoolGuy", "TheBEST", false);
    userRepo.save(user);
    songRepo.save(song);
    playlistRepo.save(playlist);

    assertNull(client1Service.playSong(1L, 1L, 1L));
  }
}
