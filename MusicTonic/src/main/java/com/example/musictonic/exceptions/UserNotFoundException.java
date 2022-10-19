package com.example.musictonic.exceptions;

public class UserNotFoundException extends RuntimeException {
    UserNotFoundException(Long user_id){
        super("Could not find user" + user_id);
    }

}
