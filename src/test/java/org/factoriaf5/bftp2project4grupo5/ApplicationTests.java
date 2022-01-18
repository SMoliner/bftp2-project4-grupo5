package org.factoriaf5.bftp2project4grupo5;

import org.factoriaf5.bftp2project4grupo5.repositories.Game;
import org.factoriaf5.bftp2project4grupo5.repositories.LegacyGameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    LegacyGameRepository legacyGameRepository;

    @BeforeEach
    void setUp() {
        legacyGameRepository.deleteAll();
    }

    @Test
    @WithMockUser
    void loadsTheHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    @WithMockUser
    void returnsTheExistingGames() throws Exception {
        Game game = legacyGameRepository.save(new Game("Duck Hunt2", "Nes", 1984,"Shooter",12, 100, "image.png"  ));

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(view().name("games/all"))
                .andExpect(model().attribute(  "games", hasItem(game)));
    }

    @Test
    @WithMockUser
    void returnsAFormToAddNewGames() throws Exception {
        mockMvc.perform(get("/games/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("games/edit"))
                .andExpect(model().attributeExists("game"))
                .andExpect(model().attribute("title", "Create new game"));
    }

    @Test
    @WithMockUser
    void allowsToCreateANewGames() throws Exception {
        mockMvc.perform(post("/games/new")
                        .param("title", "Duck Hunt2")
                        .param("Platform", "Nes")
                        .param("year", "1984")
                        .param("category", "Shooter")
                        .param("pegi", "12")
                        .param("price", "100.0")
                        .param( "image", "image.png"  )
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/games"))
        ;

        List<Game> existingGames = (List<Game>) legacyGameRepository.findAll();
        assertThat(existingGames, contains(allOf(
                hasProperty("title", equalTo("Duck Hunt2")),
                hasProperty("platform", equalTo("Nes")),
                hasProperty("year", equalTo(1984)),
                hasProperty("category", equalTo("Shooter")),
                hasProperty("pegi", equalTo(12)),
                hasProperty("price", equalTo(100.0)),
                hasProperty("image", equalTo( "image.png"))
        )));
    }

    @Test
    @WithMockUser
    void returnsAFormToEditGames() throws Exception {
        Game game = legacyGameRepository.save(new Game("Duck Hunt2", "Nes", 1984, "Shooter", 12, 100, "image.png"));
        mockMvc.perform(get("/games/" + game.getId()+"/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("games/edit"))
                .andExpect(model().attribute("game", game))
                .andExpect(model().attribute("title", "Edit game"));
    }


    @Test
    @WithMockUser
    void allowsToDeleteAGame() throws Exception {
        Game game = legacyGameRepository.save(new Game("Duck Hunt2", "Nes", 1984, "Shooter", 12, 100.0, "image.png"));
        mockMvc.perform(get("/" + game.getId()+"/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }


    @Test
    void anonymousUsersCannotCreateAGame() throws Exception {
        mockMvc.perform(post("/games/new")
                        .param("title", "Duck Hunt2")
                        .param("platform", "Nes")
                        .param("year", "1984")
                        .param("category", "Shooter")
                        .param("price", "100.0")
                        .param("image", "image.png")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}

