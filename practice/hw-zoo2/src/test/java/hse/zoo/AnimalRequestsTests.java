package hse.zoo;

import hse.zoo.application.interfaces.AnimalRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ZooApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AnimalRequestsTests {
    @Autowired
    MockMvc mockMvc;

    void createTestAnimal() throws Exception {
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
    }

    @Test
    @Order(1)
    void testCreate() throws Exception {
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

        mockMvc.perform(post("/animals").contentType(MediaType.APPLICATION_JSON).content(createJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.species").value("Siren intermedia"))
                .andExpect(jsonPath("$.nickname").value("Bobik"))
                .andExpect(jsonPath("$.favoriteFood").value("Meat"))
                .andExpect(jsonPath("$.status").value("HEALTHY"))
                .andExpect(jsonPath("$.gender").value("MALE"));


    }

    @Test
    @Order(2)
    void testGetAll() throws Exception {
        createTestAnimal();
        mockMvc.perform(get("/animals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nickname").value("Bobik"));
    }

    @Test
    @Order(3)
    void testGetOne() throws Exception {
        createTestAnimal();
        mockMvc.perform(get("/animals/{nickname}", "Bobik"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickname").value("Bobik"));
    }

    @Test
    @Order(4)
    void testDelete() throws Exception {
        createTestAnimal();
        mockMvc.perform(delete("/animals/{nickname}", "Bobik"))
                .andExpect(status().isNoContent());
    }

}
