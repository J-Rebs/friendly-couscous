package com.example.musictonic.jwt;


import com.example.musictonic.model.ClientLogin;
import com.example.musictonic.repository.ClientLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Class that holds authorities and related details for client logins.
 */
@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  ClientLoginRepository clientLoginRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final ClientLogin appUser = clientLoginRepo.findByClientName(username);

    if (appUser == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    return org.springframework.security.core.userdetails.User//
        .withUsername(username)//
        .password(appUser.getPassword())//
        .authorities(ClientRole.ROLE_ADMIN)//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
