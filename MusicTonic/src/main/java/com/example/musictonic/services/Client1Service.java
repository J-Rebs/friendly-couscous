package com.example.musictonic.services;

import com.example.musictonic.model.*;
import com.example.musictonic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
    AnalyticsSongRepository analyticsSongRepo;

    @Autowired
    AnalyticsUserRepository analyticsUserRepo;

    @Autowired
    AnalyticsPlaylistRepository analyticsPlaylistRepo;

    @Autowired
    SongRepository songRepo;

    // post operation -- play songs
    public Analytics playSong(Long userID, Long songId) {

        // insert into analytics (or returns the id)
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        Analytics a = analyticsRepo.save(new Analytics(timestamp));

        // insert into analytics song with the parameters of song id and new analytics id
        Song song = songRepo.findBySongId(songId);
        AnalyticsSong analyticsSong = new AnalyticsSong(a, song);
        analyticsSongRepo.save(analyticsSong);


        // insert into analytics user with the parameters of user id and new analytics id
        User user = userRepo.findByUserId(userID);
        AnalyticsUser analyticsUser = new AnalyticsUser(a, user);
        analyticsUserRepo.save(analyticsUser);

        // insert into analytics playlist with the parameters of playlist id and new analytics id

        return a;
    }

}
