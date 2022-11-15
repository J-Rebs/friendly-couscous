package com.example.musictonic.security;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

/*
 * Validates that the JWT token contains the intended audience in its claims.
 */

/*
 * OAuth2TokenValidator interface and its method validate provide means to verify custom OAuth 2.0
 * Token attributes. With the class above, you ensure only tokens containing the specified
 * audience, or aud claim to be exact, are valid.
 * Src: https://auth0.com/blog/spring-boot-authorization-tutorial-secure-an-api-java/
 */
class AudienceValidator implements OAuth2TokenValidator<Jwt> {
  private final String audience;

  AudienceValidator(String audience) {
    this.audience = audience;
  }

  public OAuth2TokenValidatorResult validate(Jwt jwt) {
    OAuth2Error error = new OAuth2Error("invalid_token", "The required audience is missing", null);

    if (jwt.getAudience().contains(audience)) {
      return OAuth2TokenValidatorResult.success();
    }

    return OAuth2TokenValidatorResult.failure(error);
  }
}