package com.example.musictonic.controller;


import com.example.musictonic.Utils.PopularSongsReturn;
import com.example.musictonic.Utils.UserExportReturn;
import com.example.musictonic.services.Client2Service;
import com.example.musictonic.services.Client3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Request handler for client type 3 (e.g., raw data dump).
 */
@RestController
@RequestMapping("client3-rest")
public class Client3Controller {

  @Autowired
  Client3Service client3Service;

  /**
   * Method to get and return user (i.e., type 1 client) information.
   *
   * @param userId - the unique ID for this client (i.e., user)
   * @return the analytics history for this client
   */
  @GetMapping("/userexport")
  @ResponseBody
  public ResponseEntity<UserExportReturn> getUserInformation(
      @RequestParam(name = "userid") String userId) {
    try {
      UserExportReturn response = client3Service.getUserInformation(Long.parseLong(userId));
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }
}
