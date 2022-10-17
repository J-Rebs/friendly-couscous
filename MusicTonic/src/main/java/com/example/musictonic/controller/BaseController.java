package com.example.musictonic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @RequestMapping("/")
    public String home() {
        return "See here's a new message.";
    }

}
