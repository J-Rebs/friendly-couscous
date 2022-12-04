package com.example.musictonic.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Generate tokens for use.
 */
@Component
public class JwtTokenProvider {

  /**
   * Comment from Src: https://github.com/murraco/spring-boot-jwt
   * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
   * microservices environment, this key would be kept on a config-server.
   */
  @Value("${security.jwt.token.secret-key:secret-key}")
  private String secretKey;

  @Value("${security.jwt.token.expire-length:3600000}")
  private long validityInMilliseconds = 3600000; // 1h

  @Autowired
  private MyUserDetails myUserDetails;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  /**
   * Generate JWT bearer token.
   *
   * @param username - client username
   * @param password - client password
   * @return JWT token.
   */
  public String createToken(String username, String password) {

    Claims claims = Jwts.claims().setSubject(username);

    claims.put("auth", new SimpleGrantedAuthority(ClientRole.ROLE_ADMIN.getAuthority()));

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()//
        .setClaims(claims)//
        .setIssuedAt(now)//
        .setExpiration(validity)//
        .signWith(SignatureAlgorithm.HS256, secretKey)//
        .compact();
  }

  /**
   * Add authentication of authorities.
   *
   * @param token - String for processing
   * @return UsernamePasswordAuthenticationToken object based on user Authorities.
   */
  public Authentication getAuthentication(String token) {
    UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  /**
   * Get username.
   *
   * @param token - String for processing
   * @return parsed username string.
   */
  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  /**
   * Resolve Token.
   *
   * @param req - HttpServletRequest request
   * @return see if Bearer token properly provided or return null.
   */
  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  /**
   * Validate token.
   *
   * @param token - String for processing
   * @return check token is valid or throw exception.
   */
  public boolean validateToken(String token) throws Exception {
    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      throw new Exception("Expired or invalid JWT token");
    }
  }

}
