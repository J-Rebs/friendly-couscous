# COMSW4156 - Advanced Software Engineering

## Final Project: MusicTonic Service

## Public Access Point: https://music-tonic.herokuapp.com/

### Team Members  

Ryan Soeyadi [rs4163], Joseph Rebagliati [jr4162], Yuhao Dong [yd2626], Madison Thantu [mgt2143]

## 1. Documented API

### MUSIC PLATFORM SERVICES (Client type one in our proposal)
- TYPE OF REQUEST: `GET` 
  - ROUTE: `/client1/rest/playsong` 
  - PURPOSE: Gets users available who can play song
  - FUTURE Development: N/A
- TYPE OF REQUEST: `POST` 
-   ROUTE: `/client1/rest/playsong?userid={id}&songid={id}&playlistid={id}` 
-   PURPOSE: Registers a song played in the analytics relation for a given user, song, and playlist 
-   FUTURE Development: implementation of authentication layer for a client

### MUSIC ANALYTICS SERVICES (Client type two in our proposal)

### DATA SHARING SERVICES  (Client type three in our proposal)

## 2. Unit Tests

The routes above are tested at the controller and service layer where applicable. Tests are run using J-Unit and can be run by executive the maven tests command in IntelliJ. Whenever our application deploys to Heroku, it builds and deploys the Maven project with all unit tests run as well. 

Future development will add higher levels of coverage for our service.

To view our tests look [here]('https://github.com/J-Rebs/friendly-couscous/tree/main/MusicTonic/src/test/java/com/example/musictonic').

## 3. Integration Tests

## 4. Style Compliant

We are using CheckStyle in Intelli-J to check our style compliance.

## 5. Build, Run, Test Instructions

To run the project, we recommend downloading IntelliJ, see [here](https://www.jetbrains.com/idea/). From here deployment can be done by click `run` on MusicTonicApplication. This will build and run the application. Tests can be run independently of build, but they are included in the prior instruction by default for unit testing. To run tests independently, you can use the Maven Tool Window and run Maven Tests. Below are screenshots to show this in IntelliJ. 

### Build and Run in IntelliJ

![image](https://user-images.githubusercontent.com/84640075/197676982-2d11ce27-ec65-4ad3-bf0e-e516858d6eaa.png)

### Test in IntelliJ

![image](https://user-images.githubusercontent.com/84640075/197677593-b653a00f-21d8-45eb-8775-ffed29003ab1.png)


