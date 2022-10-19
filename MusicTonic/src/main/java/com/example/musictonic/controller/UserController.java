package com.example.musictonic.controller;

import com.example.musictonic.model.User;
import com.example.musictonic.repository.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * "To wrap your repository with a web layer, you must turn to Spring MVC.
 * Thanks to Spring Boot, there is little in infrastructure to code. Instead, we can focus on actions[.]"
 * SRC: https://spring.io/guides/tutorials/rest/
 *
 * */
@RestController // " indicates that the data returned by each method will be written straight into the response body instead of rendering a template. " (Same src as above)
public class UserController {

    private final UserRepository repository;

    // You inject the repository into the controller and then use the routes around the repository to have a RESTFUL API
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @CrossOrigin
    @RequestMapping("/users")
    // Src of method: https://github.com/rcoppy/demo-persistent-data-api/blob/main/src/main/java/com/alexrupp/persistentdataapi/controllers/ChatUserController.java
    List<String> all() {
        List<String> l = new ArrayList<String>();

        for (User u : repository.findAll()) {
            l.add(u.getRealName());
        }

        return l;
    }

}
