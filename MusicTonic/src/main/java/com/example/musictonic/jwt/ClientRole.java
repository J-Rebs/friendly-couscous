package com.example.musictonic.jwt;

import org.springframework.security.core.GrantedAuthority;

/**
 * <h3>This is the client role -- used for controlling permissions.</h3>
 * Note: required for compliance with checkstyle
 */
public enum ClientRole implements GrantedAuthority {
  ROLE_ADMIN, ROLE_CLIENT;

  public String getAuthority() {
    return name();
  }

}