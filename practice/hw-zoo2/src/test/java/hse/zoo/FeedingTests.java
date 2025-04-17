package hse.zoo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ZooApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FeedingTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testFeeding() throws Exception {
        String createJson = """
                {
                   "nickname": "Bobik",
                   "species": "Siren intermedia",
                   "birthDate": "2022-12-12",
                   "gender": "MALE",
                   "favouriteFood": "Meat",
                   "status": "HEALTHY"
                }                
                """;
        mockMvc.perform(post("/animals").contentType(MediaType.APPLICATION_JSON).content(createJson));

        String feedJson = """
                {
                  "animalNickname": "Bobik",
                  "food": "Meat",
                  "date": "2022-12-12"
                }
                """;
        mockMvc.perform(post("/feeding").contentType(MediaType.APPLICATION_JSON).content(feedJson)).andExpect(status().isOk());

        mockMvc.perform(post("/feeding/feed")).andExpect(status().isOk());
    }
}
