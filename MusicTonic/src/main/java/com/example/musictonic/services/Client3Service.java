package com.example.musictonic.services;

import com.example.musictonic.Utils.AnalyticsInfoBasic;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    List<AnalyticsUser> analyticsUserList = analyticsUserRepo.findByUser(user);
    List<Analytics> analytics = new ArrayList<>();
    List<AnalyticsInfoBasic> analyticsInfoList = new ArrayList<>();

    if (analyticsUserList.size() == 0) {
    } else {
      AnalyticsUser analyticsUser = analyticsUserList.get(0);
      analytics = analyticsRepo.findAllByAnalyticsUser(analyticsUser);

      for (Analytics a : analytics) {
        Long id = a.getAnalyticsId();
        String entryTimestamp = a.getTimestamp();
        AnalyticsUser entryAU = a.getAnalyticsUser();
        User entryUser = entryAU.getUser();
        AnalyticsInfoBasic entry = new AnalyticsInfoBasic(id, entryTimestamp, entryUser);
        analyticsInfoList.add(entry);
      }
    }
    return new UserExportReturn(user, playlists, analyticsInfoList);
  }
}
