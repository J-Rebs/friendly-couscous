package com.example.musictonic.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.AnalyticsPlaylist;
import com.example.musictonic.model.AnalyticsSong;
import com.example.musictonic.model.AnalyticsUser;
import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.Song;
import com.example.musictonic.model.User;
import com.example.musictonic.model.UserType;
import com.example.musictonic.repository.*;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.lang.Integer.valueOf;

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

    @Mock
    PlaylistToSongRepository playlistToSongRepository;


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
        playlist = new Playlist(1L, 1L, "TheBEST", false);

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

    @Test
    @DisplayName("playSong() FAILS, as expected")
    void playSongBad() {
        Analytics aReturn = client1Service.playSong(1L, 1L, 1L);
        assertNull(aReturn);
    }

    @Test
    @DisplayName("likeSong() WORKS")
    void likeSongGood() throws IllegalAccessException {
        when(songRepo.findBySongId(any(Long.class))).thenReturn(song);

        Integer originalSongLikesCount = song.getSongLikesCount();
        for (int i = 0; i < 9; i++) {
            client1Service.likeSong(1L, 1L);
        }
        Integer songLikesCount = client1Service.likeSong(1L, 1L);

        assertEquals(originalSongLikesCount + 10, songLikesCount);
    }

    @Test
    @DisplayName("likeSong() FAILS, as expected")
    void likeSongBad() throws IllegalAccessException {
        when(songRepo.findBySongId(any(Long.class))).thenReturn(song);

        Integer originalSongLikesCount = song.getSongLikesCount();
        for (int i = 0; i < 9; i++) {
            client1Service.likeSong(1L, 1L);
        }
        Integer songLikesCount = client1Service.likeSong(1L, 1L);

        assertNotEquals(originalSongLikesCount + 8, songLikesCount);
    }


}
