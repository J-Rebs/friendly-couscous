package com.example.musictonic.controller;

import com.example.musictonic.repository.UserRepository;
import com.example.musictonic.services.Client1Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = Client1Controller.class)
class Client1ControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepo;

    @MockBean
    private Client1Service client1Service;

    @Test
    void playSongTest() throws Exception {
        this.mvc.perform(get("/client1-rest/playsong"))
                .andExpect(status().isOk());
    }

    @Test
    void createAnalyticalSongBad() throws Exception {
        mvc.perform(post("/client1-rest/playsong?userid=1&songid=1&playlistid=1"))
                .andExpect(status().isBadRequest());
    }

}






















