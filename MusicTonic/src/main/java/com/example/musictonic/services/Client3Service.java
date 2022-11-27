package com.example.musictonic.services;


import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.AnalyticsUser;
import com.example.musictonic.model.Client;
import com.example.musictonic.model.ClientUser;
import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.User;
import com.example.musictonic.repository.AnalyticsRepository;
import com.example.musictonic.repository.AnalyticsUserRepository;
import com.example.musictonic.repository.ClientRepository;
import com.example.musictonic.repository.ClientUserRepository;
import com.example.musictonic.repository.PlaylistRepository;
import com.example.musictonic.repository.UserRepository;
import com.example.musictonic.utils.AnalyticsInfoBasic;
import com.example.musictonic.utils.UserExportReturn;
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

/**
 * Class for Client1Service.
 */
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

  @Autowired
  ClientUserRepository clientUserRepo;

  @Autowired
  ClientRepository clientRepo;

  /**
   * Get and return information (i.e., playlists and analytics history) for this client.
   *
   * @param userId   - the unique ID for this client (i.e., user)
   * @param clientId - the ID for the client that has the user in question
   * @return a UserExportReturn object containing the playlists and analytics history
   */
  public UserExportReturn getUserInformation(Long userId, Long clientId)
      throws IllegalAccessException {
    // client information
    Client client = clientRepo.findByClientId(clientId);

    // user information
    User user = userRepo.findByUserId(userId);
    List<ClientUser> usersForClient = clientUserRepo.findAllByClient(client);

    // check that user exists for client; else throw exception
    boolean clientUserMatch = false;
    for (ClientUser cl : usersForClient) {
      if (cl.getUser().getUserId() == userId && cl.getClient().getClientId() == clientId) {
        clientUserMatch = true;
        break;
      }
    }

    if (!clientUserMatch) {
      throw new IllegalAccessException("user doesn't exist for this client");
    }

    // assume all playlists that owned by user are available to client
    // same applies to analytics -- assumption is user entry unique to client, so, therefore analytics
    // entries also belong to client
    List<Playlist> playlists = playlistRepo.findAllByOwner(userId);
    List<AnalyticsUser> analyticsUserList = analyticsUserRepo.findByUser(user);
    List<Analytics> analytics = new ArrayList<>();
    List<AnalyticsInfoBasic> analyticsInfoList = new ArrayList<>();

    if (analyticsUserList.size() > 0) {
      for (AnalyticsUser au : analyticsUserList) {
        analytics.add(analyticsRepo.findByAnalyticsUser(au));
      }

      for (Analytics a : analytics) {
        Long id = a.getAnalyticsId();
        String entryTimestamp = a.getTimestamp();
        AnalyticsUser entryAu = a.getAnalyticsUser();
        User entryUser = entryAu.getUser();
        AnalyticsInfoBasic entry = new AnalyticsInfoBasic(id, entryTimestamp, entryUser);
        analyticsInfoList.add(entry);
      }
    }
    return new UserExportReturn(user, playlists, analyticsInfoList);
  }
}
