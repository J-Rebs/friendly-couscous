package com.example.musictonic.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Client authentication controller.
 */
@RestController
@CrossOrigin
@RequestMapping("/client-auth")
public class JwtController {

  @Autowired
  private JwtService jwtService;

  /**
   * Sign in route.
   *
   * @param username - client username
   * @param password - client password
   * @return JWT token
   * @throws Exception cannot sign in
   */

  @PostMapping("/signin")
  public String login(//
                      @RequestParam(name = "username") String username,
                      @RequestParam(name = "password") String password) throws Exception {
    return jwtService.signin(username, password);
  }

  /**
   * Sign up route.
   *
   * @param username - client username
   * @param password - client password
   * @return JWT token
   * @throws Exception cannot sign up
   */
  @PostMapping("/signup")
  public String signup(@RequestParam(name = "username") String username,
                       @RequestParam(name = "password") String password) throws Exception {
    return jwtService.signup(username, password);
  }


}
