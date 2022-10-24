package com.example.musictonic.controller;

import com.example.musictonic.services.Client1Service;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



/* SRC: https://stackoverflow.com/questions/44200720/difference-between-mock-mockbean-and-mockito-mock */

@RunWith(SpringRunner.class)
@WebMvcTest(Client1Controller.class)
class Client1ControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    Client1Service client1Service;

    @Test
    void playSongGood() throws Exception {
        mvc.perform(get("/client1-rest/playsong"))
                .andExpect(status().isOk());
    }

 /*   @Test
    void playSongBad() throws Exception {
        mvc.perform(get("/client1-rest/playsong?userid=1234234&songid=1&playlistid=1").header("User-Agent", "PostmanRuntime/7.6.0"))
                .andExpect(status().isBadRequest());
    }
*/
}