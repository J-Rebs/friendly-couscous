package com.example.musictonic.integration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}