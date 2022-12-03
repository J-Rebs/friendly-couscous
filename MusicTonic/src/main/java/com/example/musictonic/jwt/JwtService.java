package com.example.musictonic.jwt;

import com.example.musictonic.model.ClientLogin;
import com.example.musictonic.repository.ClientLoginRepository;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JwtService {

  @Autowired
  private ClientLoginRepository clientLoginRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  public String signin(String username, String password) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider.createToken(username, password);
    } catch (AuthenticationException e) {
      throw new Exception("Invalid username/password supplied");
    }
  }

  public String signup(String username, String password) throws Exception {
    if (!clientLoginRepository.existsByClientName(username)) {
      ClientLogin registeredClient = new ClientLogin(username, password);
      registeredClient.setPassword(passwordEncoder.encode(registeredClient.getPassword()));
      clientLoginRepository.save(registeredClient);
      return jwtTokenProvider.createToken(registeredClient.getClientName(),
          registeredClient.getPassword());
    } else {
      throw new Exception("Username is already in use");
    }
  }


}
