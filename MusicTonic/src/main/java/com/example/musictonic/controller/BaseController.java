package com.example.musictonic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Default request handler.
 */
@RestController
public class BaseController {

  @RequestMapping("/")
  public String home() {
    return "I'm a software engineer";
  }

  @RequestMapping("/authorized")
  public String homeAuthorized() {
    return "I'm an AUTHORIZED software engineer";
  }

}
