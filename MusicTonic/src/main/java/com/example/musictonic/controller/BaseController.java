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
    return "Hello! I'm a software engineer";
  }

}
