package com.example.musictonic.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Disabled("Disabled for integration testing purposes")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
  @Autowired
  private TestRestTemplate testRestTemplate = new TestRestTemplate();


  @Test
  public void getUsers() {
    String url = "https://music-tonic.herokuapp.com/client1-rest/listUsers";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).build();
    // if we need input parameter
    // UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("param", "key").build();
    HttpEntity<String> req = new HttpEntity<>(null, null);

    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.GET, req, String.class);

    System.out.println(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void playSong() {
    String url = "https://music-tonic.herokuapp.com/client1-rest/playsong";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
        .queryParam("userid", "1")
        .queryParam("songid", "1")
        .queryParam("playlistid", "1")
        .queryParam("clientid", "1").build();
    HttpEntity<String> req = new HttpEntity<>(null, null);

    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.POST, req, String.class);

    System.out.println(response.getBody());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  public void likeSong() {
    String url = "https://music-tonic.herokuapp.com/client1-rest/likeSong";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
        .queryParam("userid", "1")
        .queryParam("songid", "1")
        .queryParam("clientid", "1").build();
    HttpEntity<String> req = new HttpEntity<>(null, null);

    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.PUT, req, String.class);

    System.out.println(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void createUsers() {
    String url = "https://music-tonic.herokuapp.com/client1-rest/createUser";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
        .queryParam("realname", "Apple")
        .queryParam("usertype", "LISTENER")
        .queryParam("maingenre", "pop")
        .queryParam("age", "16")
        .queryParam("clientid", "1").build();
    HttpEntity<String> req = new HttpEntity<>(null, null);

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
    String url = "https://music-tonic.herokuapp.com/client2-rest/top3songs";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).build();
    HttpEntity<String> req = new HttpEntity<>(null, null);

    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.GET, req, String.class);

    System.out.println(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  // Client 3
  @Test
  public void exportUsers() {
    String url = "https://music-tonic.herokuapp.com/client3-rest/userexport";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
        .queryParam("userid", "1")
        .queryParam("clientid", "1").build();
    HttpEntity<String> req = new HttpEntity<>(null, null);

    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.GET, req, String.class);

    System.out.println(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void listSongs() {
    String url = "https://music-tonic.herokuapp.com/client3-rest/listSongs";

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).build();
    HttpEntity<String> req = new HttpEntity<>(null, null);

    ResponseEntity<String> response =
        testRestTemplate.exchange(builder.toString(), HttpMethod.GET, req, String.class);

    System.out.println(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
}