package com.example.musictonic.jwt;

import com.example.musictonic.model.Client;
import com.example.musictonic.model.ClientLogin;
import com.example.musictonic.repository.ClientLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private ClientLoginRepository userRepository;

  @Override
  public void saveClientLogin(ClientLogin clientLogin) {
    userRepository.save(clientLogin);
  }

  @Override
  public ClientLogin getUserByNameAndPassword(String name, String password) throws
      UsernameNotFoundException {
    ClientLogin user = userRepository.findByClientNameAndPassword(name, password);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid id and password");
    }
    return user;
  }
}
