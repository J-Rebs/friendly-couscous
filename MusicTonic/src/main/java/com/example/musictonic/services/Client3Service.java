package com.example.musictonic.services;

import com.example.musictonic.Utils.PopularSongsReturn;
import com.example.musictonic.Utils.UserExportReturn;
import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.AnalyticsUser;
import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.Song;
import com.example.musictonic.model.User;
import com.example.musictonic.repository.AnalyticsRepository;
import com.example.musictonic.repository.AnalyticsUserRepository;
import com.example.musictonic.repository.PlaylistRepository;
import com.example.musictonic.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *
 *  WANT TO HAVE:
 *   - user data dump
 *   - if have time want to add authorization but can also be in second iteration, not critical
 *
 *  DEPEND ON:
 *  - client 1
 *  - authorization how to
 *
 * */
@Service
public class Client3Service {

  @Autowired
  UserRepository userRepo;

  @Autowired
  PlaylistRepository playlistRepo;

  @Autowired
  AnalyticsUserRepository analyticsUserRepo;

  @Autowired
  AnalyticsRepository analyticsRepo;


  public UserExportReturn getUserInformation(Long userId) {

    User user = userRepo.findByUserId(userId);
    List<Playlist> playlists = playlistRepo.findAllByOwner(userId);
    AnalyticsUser analyticsUser = analyticsUserRepo.findByUser(user);
    List<Analytics> analytics = analyticsRepo.findAllByAnalyticsUser(analyticsUser);

    return new UserExportReturn(user, playlists, analytics);
  }
}
