package com.example.musictonic.controller;

import com.example.musictonic.model.User;
import com.example.musictonic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @RequestMapping("/totalUsers")
    public ResponseEntity<?> users() {
        try{
            List<User> listStudents = userRepo.findAll();
            return new ResponseEntity<>(listStudents, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
