package com.example.musictonic.jwt;


import com.example.musictonic.model.ClientLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("client-auth")
public class UserController {
  private UserService userService;
  private JwtGeneratorInterface jwtGenerator;

  @Autowired
  public UserController(UserService userService, JwtGeneratorInterface jwtGenerator) {
    this.userService = userService;
    this.jwtGenerator = jwtGenerator;
  }

  @PostMapping("/register")
  public ResponseEntity<?> postUser(@RequestParam(name = "clientName") String clientName,
                                    @RequestParam(name = "password") String password) {
    ClientLogin user = new ClientLogin(clientName, password);
    try {
      userService.saveClientLogin(user);
      return new ResponseEntity<>(user, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestParam(name = "clientName") String clientName,
                                     @RequestParam(name = "password") String password) {
    try {
      ClientLogin user = new ClientLogin(clientName, password);
      if (user.getClientName() == null || user.getPassword() == null) {
        throw new UsernameNotFoundException("UserName or Password is Empty");
      }
      ClientLogin userData =
          userService.getUserByNameAndPassword(user.getClientName(), user.getPassword());
      if (userData == null) {
        throw new UsernameNotFoundException("UserName or Password is Invalid");
      }
      return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
    } catch (UsernameNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }
}
