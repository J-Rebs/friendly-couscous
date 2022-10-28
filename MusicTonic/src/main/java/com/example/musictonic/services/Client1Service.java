package com.example.musictonic.services;

import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.AnalyticsPlaylist;
import com.example.musictonic.model.AnalyticsSong;
import com.example.musictonic.model.AnalyticsUser;
import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.Song;
import com.example.musictonic.model.User;
import com.example.musictonic.repository.AnalyticsPlaylistRepository;
import com.example.musictonic.repository.AnalyticsRepository;
import com.example.musictonic.repository.AnalyticsSongRepository;
import com.example.musictonic.repository.AnalyticsUserRepository;
import com.example.musictonic.repository.PlaylistRepository;
import com.example.musictonic.repository.SongRepository;
import com.example.musictonic.repository.UserRepository;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 *
 *  WANT TO HAVE:
 *  - ability to modify and retrieve (e.g., registering users, making groups, etc. etc.) from database as well as a function to:
 *  - like a song (creates if not exists default playlist by user and adds to that),
 *  - play a song (triggers an entry in analytics)
 *  - maybe nice to have authorization in round 2 if we have time
 *  DEPEND ON:
 *  - finish the models,
 *  - exceptions,
 *  - controllers, and repositories for rest of our ER diagram,
 *  - write a function for liking a song and playing a song
 *
 *  USER: Johnny --> CLIENT INTERFACE: Spotify <---> MusicTonic <---> communicates with database
 *  Johnny playing a song ~ in postman to doing a post route for a song
 *
 * */
@Service
public class Client1Service {
    // Repositories - analytics, song, user
    @Autowired
    UserRepository userRepo;
    @Autowired
    AnalyticsRepository analyticsRepo;

    @Autowired
    AnalyticsSongRepository analyticsSongRepo;

    @Autowired
    AnalyticsUserRepository analyticsUserRepo;

    @Autowired
    AnalyticsPlaylistRepository analyticsPlaylistRepo;

    @Autowired
    SongRepository songRepo;

    @Autowired
    PlaylistRepository playlistRepo;

    public Integer likeSong(Long userId, Long songId) {
        Song song = songRepo.findBySongId(songId);
        Integer songLikesCount = song.getSongLikesCount();
        songLikesCount += 1;
        song.setSongLikesCount(songLikesCount);
        songRepo.save(song);
        return songLikesCount;
    }


    // post operation -- play songs
    public Analytics playSong(Long userId, Long songId, Long playlistId) {

        // insert into analytics (or returns the id)
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        Analytics a = analyticsRepo.save(new Analytics(timestamp));

        // insert into analytics song with the parameters of song id and new analytics id
        Song song = songRepo.findBySongId(songId);
        AnalyticsSong analyticsSong = new AnalyticsSong(a, song);
        analyticsSongRepo.save(analyticsSong);

        // insert into analytics user with the parameters of user id and new analytics id
        User user = userRepo.findByUserId(userId);
        AnalyticsUser analyticsUser = new AnalyticsUser(a, user);
        analyticsUserRepo.save(analyticsUser);

        // insert into analytics playlist with the parameters of playlist id and new analytics id
        Playlist playlist = playlistRepo.findByPlaylistId(playlistId);
        AnalyticsPlaylist analyticsPlaylist = new AnalyticsPlaylist(a, playlist);
        analyticsPlaylistRepo.save(analyticsPlaylist);

        try {
            a.setAnalyticsUser(analyticsUser);
            a.setAnalyticsSong(analyticsSong);
            a.setAnalyticsPlaylist(analyticsPlaylist);
        } catch (Exception e) {
            return a;
        }

        return a;
    }
}
