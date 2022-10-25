# COMSW4156 - Advanced Software Engineering

## Final Project: MusicTonic Service

Team Members: Ryan Soeyadi [rs4163],
Joseph Rebagliati [jr4162], Yuhao Dong [yd2626], Madison Thantu [mgt2143]

## 1. Documented API

### CLIENT 1 ###
- TYPE OF REQUEST: `GET` 
  - ROUTE: `/client1/rest/playsong` 
  - PURPOSE: Gets users available who can play song
  - FUTURE Development: N/A
- TYPE OF REQUEST: `POST` 
-   ROUTE: `/client1/rest/playsong?userid={id}&songid={id}&playlistid={id}` 
-   PURPOSE: Registers a song played in the analytics relation for a given user, song, and playlist 
-   FUTURE Development: implementation of authentication layer for a client

### CLIENT 2 ###

### CLIENT 3 ###

## 2. Unit Tests

The routes above are tested at the controller and service layer where applicable. Tests are run using J-Unit and can be run by executive the maven tests command in Intelli-J. Whenever our application deploys to Heroku, it builds and deploys the Maven project with all unit tests run as well. 

To view our tests look [here]('https://github.com/J-Rebs/friendly-couscous/tree/main/MusicTonic/src/test/java/com/example/musictonic')

## 3. Integration Tests

## 4. Style Compliant

We are using CheckStyle in Intelli-J to check our style compliance.

## 5. Build, Run, Test Instructions
