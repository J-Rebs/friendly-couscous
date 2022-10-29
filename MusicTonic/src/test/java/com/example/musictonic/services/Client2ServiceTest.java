package com.example.musictonic.services;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.musictonic.Utils.PopularSongsReturn;
import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.PlaylistToSongs;
import com.example.musictonic.model.Song;
import com.example.musictonic.repository.PlaylistToSongRepository;
import com.example.musictonic.repository.SongRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Client2ServiceTest {

  @Mock
  SongRepository songRepo;

  @Mock
  PlaylistToSongRepository playlistToSongRepo;

  @InjectMocks
  Client2Service client2Service;

  private Song song1;
  private Song song2;
  private Song song3;


  private Playlist playlist;
  private PlaylistToSongs playpop1;
  private PlaylistToSongs playpop2;
  private PlaylistToSongs playpop3;

  List<PlaylistToSongs> popularPlaylists = new ArrayList<>();

  List<Song> top3 = new ArrayList<>();

  @BeforeEach
  void init() {
    song1 = new Song("SongySongyPopPop", 2, "CoolestArtist", "YaYaMerchMakesYou COOL", 10);
    song2 = new Song("CoffeeYolo", 2, "CoolestArtist",
        "Caffeine is like cash both start with a C and end with an E or H", 5);
    song3 = new Song("Sawgeroo", 5, "CoolestArtist", "Valgrind all day and all night", 5);
    top3.add(song1);
    top3.add(song2);
    top3.add(song3);

    playlist = new Playlist(1L, "TheBEST", false);
    playpop1 = new PlaylistToSongs(song1, playlist);

    // only need 1 entry because simulating as if each song was in the same playlist
    popularPlaylists.add(playpop1);


  }

  @Test
  @DisplayName("getMostPopularSongs() WORKS")
  void getMostPopularSongsGood() {
    when(songRepo.findTop3ByOrderBySongLikesCountDesc()).thenReturn(top3);
    when(playlistToSongRepo.findAllBySong(any(Song.class))).thenReturn(popularPlaylists);
    PopularSongsReturn res = client2Service.getMostPopularSongs();
    assertNotNull(res);
    assertThat(res.getPopularSongs()).isEqualTo(top3);
    assertThat(res.getAverageNumberPlaylists()).isEqualTo(1);

  }

  @Test
  @DisplayName("getMostPopularSongs() FAILS, as expected")
  void getMostPopularSongsBad() {
    List<Song> blankList = new ArrayList<>();
    when(songRepo.findTop3ByOrderBySongLikesCountDesc()).thenReturn(blankList);
    PopularSongsReturn res = client2Service.getMostPopularSongs();
    assertNotNull(res);
    assertThat(res.getPopularSongs()).isNotEqualTo(top3);
    assertThat(res.getAverageNumberPlaylists()).isNotEqualTo(1);
  }


}
