package com.example.musictonic.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

// @Disabled("Disabled for integration testing purposes")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
  @Autowired
  private TestRestTemplate testRestTemplate = new TestRestTemplate();

  private String token = "";

  private String getToken(){
    String url = "https://music-tonic.herokuapp.com/client-auth/signup?username=client-1&password=supercool";
    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).build();
    HttpEntity<String> req = new HttpEntity<>(null, null);
    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.POST, req, String.class);

    return response.getBody();
  }

  @Test
  public void getUsers() {
    if(token.length() == 0){
      token = getToken();
    }

    String url = "https://music-tonic.herokuapp.com/client1-rest/listUsers?clientid=1";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).build();
    // if we need input parameter
    // UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("param", "key").build();
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    HttpEntity<String> req = new HttpEntity<>(null, headers);

    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.GET, req, String.class);

    System.out.println(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void playSong() {
    if(token.length() == 0){
      token = getToken();
    }

    String url = "https://music-tonic.herokuapp.com/client1-rest/playsong";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
        .queryParam("userid", "1")
        .queryParam("songid", "1")
        .queryParam("playlistid", "1")
        .queryParam("clientid", "1").build();

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    HttpEntity<String> req = new HttpEntity<>(null, headers);

    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.POST, req, String.class);

    System.out.println(response.getBody());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  public void likeSong() {
    if(token.length() == 0){
      token = getToken();
    }

    String url = "https://music-tonic.herokuapp.com/client1-rest/likeSong";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
        .queryParam("userid", "1")
        .queryParam("songid", "1")
        .queryParam("clientid", "1").build();

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    HttpEntity<String> req = new HttpEntity<>(null, headers);

    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.PUT, req, String.class);

    System.out.println(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void createUsers() {
    if(token.length() == 0){
      token = getToken();
    }

    String url = "https://music-tonic.herokuapp.com/client1-rest/createUser";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
        .queryParam("realname", "Apple")
        .queryParam("usertype", "LISTENER")
        .queryParam("maingenre", "pop")
        .queryParam("age", "16")
        .queryParam("clientid", "1").build();

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    HttpEntity<String> req = new HttpEntity<>(null, headers);

    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.POST, req, String.class);

    System.out.println(response.getBody());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  /*
  @Test
  public void deleteUsers() {
    String url = "https://music-tonic.herokuapp.com/client1-rest/deleteUser";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
        .queryParam("id", "10")
        .queryParam("clientId", "1").build();
    HttpEntity<String> req = new HttpEntity<>(null, null);

    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.DELETE, req, String.class);

    System.out.println(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
  */

  // client 2
  @Test
  public void top3Songs() {
    if(token.length() == 0){
      token = getToken();
    }

    String url = "https://music-tonic.herokuapp.com/client2-rest/top3songs";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).build();

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    HttpEntity<String> req = new HttpEntity<>(null, headers);

    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.GET, req, String.class);

    System.out.println(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  // Client 3
  @Test
  public void exportUsers() {
    if(token.length() == 0){
      token = getToken();
    }

    String url = "https://music-tonic.herokuapp.com/client3-rest/userexport";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
        .queryParam("userid", "1")
        .queryParam("clientid", "1").build();

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    HttpEntity<String> req = new HttpEntity<>(null, headers);

    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.GET, req, String.class);

    System.out.println(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void listSongs() {
    if(token.length() == 0){
      token = getToken();
    }

    String url = "https://music-tonic.herokuapp.com/client3-rest/listSongs";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).build();

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    HttpEntity<String> req = new HttpEntity<>(null, headers);

    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.GET, req, String.class);

    System.out.println(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
}