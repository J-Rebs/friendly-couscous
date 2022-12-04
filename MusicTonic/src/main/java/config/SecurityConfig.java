package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security config for using authentication. Original src: https://github.com/murraco/spring-boot-jwt,
 * but updates for deprecation based on:
 * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  /**
   * Establish proper chain of filters to make authentication work.
   *
   * @param http - HTTP security object.p
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable()) // (1)
        .authorizeRequests(auth -> auth
            .anyRequest().authenticated() // (2)
        )
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // (3)
        .httpBasic(Customizer.withDefaults()) // (4)
        .build();
  }


}