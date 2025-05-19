package hse.zoo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ZooApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AnimalTransferTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testTransfer() throws Exception {
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

        createJson = """
                            {
                              "type": "CAGE",
                              "maxCapacity": 10,
                              "volume": 100
                            }
                """;
        String resp = mockMvc.perform(post("/enclosures").contentType(MediaType.APPLICATION_JSON).content(createJson)).andReturn().getResponse().getContentAsString();
        JsonNode root = objectMapper.readTree(resp);
        int id1 = root.get("id").asInt();

        String transferJson = """
                {
                "animalNickname": "Bobik",
                "newEnclosureID": """ + id1 + """
                }
                """;
        mockMvc.perform(post("/transfer", "Bobik").contentType(MediaType.APPLICATION_JSON).content(transferJson)).andExpect(status().isOk());

    }
}
