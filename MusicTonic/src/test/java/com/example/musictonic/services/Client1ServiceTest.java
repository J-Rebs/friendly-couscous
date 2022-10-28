package com.example.musictonic.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.musictonic.model.*;
import com.example.musictonic.repository.AnalyticsPlaylistRepository;
import com.example.musictonic.repository.AnalyticsRepository;
import com.example.musictonic.repository.AnalyticsSongRepository;
import com.example.musictonic.repository.AnalyticsUserRepository;
import com.example.musictonic.repository.PlaylistRepository;
import com.example.musictonic.repository.SongRepository;
import com.example.musictonic.repository.UserRepository;
import com.zaxxer.hikari.pool.HikariProxyCallableStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
class Client1ServiceTest {

    @Mock
    AnalyticsRepository analyticsRepo;

    @Mock
    AnalyticsUserRepository analyticsUserRepo;

    @Mock
    AnalyticsSongRepository analyticsSongRepo;

    @Mock
    AnalyticsPlaylistRepository analyticsPlaylistRepo;

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

    @InjectMocks
    Client1Service client1Service;

    private User user;
    private Song song;
    private Playlist playlist;
    private Analytics a;

    private AnalyticsUser analyticsUser;
    private AnalyticsSong analyticsSong;
    private AnalyticsPlaylist analyticsPlaylist;

    private Timestamp timestamp;

    @BeforeEach
    void init() {
        user = new User(1L, "Cool Guy", UserType.ARTIST, "country", 27);
        song = new Song(1L, "SongySongyPopPop", 2, "CoolestArtist", "YaYaMerchMakesYou COOL", 10);
        playlist = new Playlist(1L, "CoolGuy", "TheBEST", false);

        analyticsUser = new AnalyticsUser(a, user);
        analyticsSong = new AnalyticsSong(a, song);
        analyticsPlaylist = new AnalyticsPlaylist(a, playlist);

        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
        a = new Analytics(timestamp);

    }

    @Test
    @DisplayName("playSong() WORKS")
    void playSongGood() {
        when(userRepo.findByUserId(any(Long.class))).thenReturn(user);
        when(songRepo.findBySongId(any(Long.class))).thenReturn(song);
        when(playlistRepo.findByPlaylistId(any(Long.class))).thenReturn(playlist);
        when(analyticsRepo.save(any(Analytics.class))).thenReturn(a);
        when(analyticsUserRepo.save(any(AnalyticsUser.class))).thenReturn(analyticsUser);
        when(analyticsSongRepo.save(any(AnalyticsSong.class))).thenReturn(analyticsSong);
        when(analyticsPlaylistRepo.save(any(AnalyticsPlaylist.class))).thenReturn(analyticsPlaylist);

        Analytics aReturn = client1Service.playSong(1L, 1L, 1L);
        assertNotNull(aReturn);
        assertThat(aReturn.getTimestamp()).isEqualTo(timestamp);
    }

}
