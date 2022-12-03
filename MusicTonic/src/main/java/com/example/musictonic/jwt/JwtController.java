package com.example.musictonic.jwt;

import com.example.musictonic.model.ClientLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/client-auth")
public class JwtController {

  @Autowired
  private JwtService jwtService;


  @PostMapping("/signin")
  public String login(//
                      @RequestParam(name = "username") String username,
                      @RequestParam(name = "password") String password) throws Exception {
    return jwtService.signin(username, password);
  }

  @PostMapping("/signup")
  public String signup(@RequestParam(name = "username") String username,
                       @RequestParam(name = "password") String password) throws Exception {
    return jwtService.signup(username, password);
  }


}
