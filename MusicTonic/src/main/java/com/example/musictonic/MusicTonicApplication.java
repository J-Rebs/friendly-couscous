package com.example.musictonic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * The main function for this program.
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MusicTonicApplication {

  public static void main(String[] args) {
    SpringApplication.run(MusicTonicApplication.class, args);
  }

}
