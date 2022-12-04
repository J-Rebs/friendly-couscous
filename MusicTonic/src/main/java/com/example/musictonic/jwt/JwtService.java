package com.example.musictonic.jwt;

import com.example.musictonic.model.ClientLogin;
import com.example.musictonic.repository.ClientLoginRepository;
import com.example.musictonic.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Authentication service that allows clients to sign up and login for bearer tokens.
 */
@Service
public class JwtService {

  @Autowired
  private ClientLoginRepository clientLoginRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private ClientRepository clientRepo;

  @Autowired
  private AuthenticationManager authenticationManager;

  /**
   * Sign in after initial sign up for authentication.
   *
   * @param username - client name
   * @param password - client password
   * @return JWT token on sign in after first sign up
   * @throws Exception if invalid username/password
   */
  public String signin(String username, String password) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider.createToken(username,
          password);
    } catch (AuthenticationException e) {
      throw new Exception("Invalid username/password supplied");
    }
  }

  /**
   * Sign up and receiving first bearer token if exist in separate manual list of allowed clients
   * and don't violate uniqueness constraints.
   *
   * @param username - client name
   * @param password - client password
   * @return JWT token on first sign up
   * @throws Exception if not valid sign up
   */
  public String signup(String username, String password) throws Exception {
    // Assume core client list is separately maintained by
    // MusicTonic to prevent people from signing up who should not
    boolean isClientAvailable = clientRepo.existsByClientName(username);
    if (!clientLoginRepository.existsByClientName(username) && isClientAvailable) {
      ClientLogin registeredClient = new ClientLogin(username, password);
      registeredClient.setPassword(passwordEncoder.encode(registeredClient.getPassword()));
      clientLoginRepository.save(registeredClient);
      return jwtTokenProvider.createToken(registeredClient.getClientName(),
          registeredClient.getPassword());
    } else {
      throw new Exception(
          "Username is already in use or is not registered with the core client list");
    }
  }


}
