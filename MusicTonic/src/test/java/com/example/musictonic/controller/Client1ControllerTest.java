package com.example.musictonic.controller;

import com.example.musictonic.config.TestProfileConfig;
import com.example.musictonic.model.Playlist;
import com.example.musictonic.model.Song;
import com.example.musictonic.model.User;
import com.example.musictonic.model.UserType;
import com.example.musictonic.repository.PlaylistRepository;
import com.example.musictonic.repository.SongRepository;
import com.example.musictonic.repository.UserRepository;
import com.example.musictonic.services.Client1Service;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
    private UserRepository userRepo;

    @MockBean
    private PlaylistRepository playlistRepo;

    @MockBean
    private Client1Service client1Service;

    @MockBean
    private SongRepository songRepo;

    @Test
    void validPlaySong() throws Exception {
        User user = new User(1L, "Cool Guy", UserType.ARTIST, "country", 27);
        Song song = new Song(1L, "SongySongyPopPop", 2, "CoolestArtist", "YaYaMerchMakesYou COOL", 10);
        Playlist playlist = new Playlist(1L, "CoolGuy", "TheBEST", false);
        userRepo.save(user);
        songRepo.save(song);
        playlistRepo.save(playlist);

        MvcResult res = mvc.perform(get("/client1-rest/playsong"))
                .andExpect(status().isOk())
                .andReturn();

        String content = res.getResponse().getContentAsString();
        System.out.print("RESULT OF GET VALID PLAYSONG" + content);
    }





}