package com.example.musictonic.jwt;

import com.example.musictonic.model.ClientLogin;

import java.util.Map;

public interface JwtGeneratorInterface {
  Map<String, String> generateToken(ClientLogin user);
}