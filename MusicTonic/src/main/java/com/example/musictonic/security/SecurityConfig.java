package com.example.musictonic.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

/**
 * Configures our application with Spring Security to restrict access to our API endpoints.
 */
@EnableWebSecurity
public class SecurityConfig {

  @Value("${auth0.audience}")
  private String audience;

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuer;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        This is where we configure the security required for our endpoints and set up our app to serve as
        an OAuth2 Resource Server, using JWT validation.
        */
    http.authorizeRequests()
        .mvcMatchers(HttpMethod.GET, "/").permitAll()
        .mvcMatchers(HttpMethod.GET, "/authorized").authenticated()
        .mvcMatchers(HttpMethod.GET, "/client1-rest/listUsers").hasAuthority("SCOPE_get:users")
        .mvcMatchers(HttpMethod.PUT, "/client1-rest/likeSong").hasAuthority("SCOPE_like:song")
        .mvcMatchers(HttpMethod.POST, "/client1-rest/playSong").hasAuthority("SCOPE_play:song")
        .mvcMatchers(HttpMethod.POST, "/client1-rest/createUser").hasAuthority("SCOPE_add:newUser")
        .mvcMatchers(HttpMethod.DELETE, "/client1-rest/deleteUser").hasAuthority("SCOPE_delete:user")
        .mvcMatchers(HttpMethod.GET, "/client2-rest/top3songs").hasAuthority("SCOPE_read:top3songs")
        .mvcMatchers(HttpMethod.GET, "/client3-rest/userExport").hasAuthority("SCOPE_get:userExport")
        .mvcMatchers(HttpMethod.GET, "/client3-rest/listSongs").hasAuthority("SCOPE_list:allSongs")
        .and().cors().configurationSource(corsConfigurationSource())
        .and().oauth2ResourceServer().jwt();
    return http.build();
  }

  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedMethods(List.of(
        HttpMethod.GET.name(),
        HttpMethod.PUT.name(),
        HttpMethod.POST.name(),
        HttpMethod.DELETE.name()
    ));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
    return source;
  }

  @Bean
  JwtDecoder jwtDecoder() {
        /*
        By default, Spring Security does not validate the "aud" claim of the token, to ensure that
        this token is indeed intended for our app.
        */

    NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
        JwtDecoders.fromOidcIssuerLocation(issuer);

    OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
    OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

    jwtDecoder.setJwtValidator(withAudience);

    return jwtDecoder;
  }
}