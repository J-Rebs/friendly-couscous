package com.example.musictonic.jwt;

import org.springframework.security.core.GrantedAuthority;

public enum ClientRole implements GrantedAuthority {
  ROLE_ADMIN, ROLE_CLIENT;

  public String getAuthority() {
    return name();
  }

}