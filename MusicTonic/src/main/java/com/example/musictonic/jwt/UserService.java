package com.example.musictonic.jwt;

import com.example.musictonic.model.Client;
import com.example.musictonic.model.ClientLogin;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  public void saveClientLogin(ClientLogin clientLogin);


  public ClientLogin getUserByNameAndPassword(String name, String password) throws
      UsernameNotFoundException;
}