package com.example.musictonic.services;

import com.example.musictonic.model.Analytics;
import com.example.musictonic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
    AnalyticsSongRepository analyticsSongRepository;

    @Autowired
    AnalyticsUserRepository analyticsUserRepository;

    @Autowired
    AnalyticsPlaylistRepository analyticsPlaylistRepository;

    @Autowired
    SongRepository songRepo;

    // post operation -- play songs
    public List<Analytics> playSong(Integer songID, Integer userID, Integer playListID) {
        // insert into analytics (or returns the id)

        // insert into analytics song with the parameters of song id and new analytics id

        // insert into analytics user with the parameters of song id and new analytics id

        // insert into analytics playlist with the parameters of song id and new analytics id
        List<Analytics> ls = new ArrayList<>();
        return ls;
    }
}
