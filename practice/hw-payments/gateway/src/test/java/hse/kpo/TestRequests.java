package hse.kpo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.services.ProxyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class TestRequests {
    @Autowired
    ProxyService service;

    @Autowired
    ObjectMapper mapper;

    ResponseEntity<String> createTestAccount(Long id) {
        return service.forwardCreateAccount(id);
    }

    @Test
    @DisplayName("create account")
    @Order(1)
    void createAccount() throws Exception {
        var resp = createTestAccount(new Random().nextLong()); // casino simulator
        Assertions.assertSame(HttpStatus.CREATED, resp.getStatusCode());
        var json = mapper.readTree(resp.getBody());
        Assertions.assertTrue(json.isObject());
        Assertions.assertTrue(json.get("id").isNumber());
        Assertions.assertEquals(0.0, json.get("balance").asDouble());
    }

    @Test
    @DisplayName("topup")
    @Order(2)
    void topup() throws Exception {
        var tmpId = new Random().nextLong();
        createTestAccount(tmpId);
        var resp = service.forwardTopup("{\"amount\":10}", tmpId);
        Assertions.assertSame(HttpStatus.OK, resp.getStatusCode());
        var json = mapper.readTree(resp.getBody());
        Assertions.assertTrue(json.isObject());
        Assertions.assertEquals(json.get("id").asLong(), (long) tmpId);
        Assertions.assertEquals(10.0, json.get("balance").asDouble());
    }

    @Test
    @DisplayName("e2e")
    void fullTest() throws JsonProcessingException, InterruptedException {
        var tmpId = new Random().nextLong();
        createTestAccount(tmpId);
        var resp = service.forwardTopup("{\"amount\":100}", tmpId);
        Assertions.assertSame(HttpStatus.OK, resp.getStatusCode());

        resp = service.forwardCreateOrder("{\"userId\":" + tmpId + ", \"amount\": 10}");
        Assertions.assertSame(HttpStatus.CREATED, resp.getStatusCode());
        var orderId = mapper.readTree(resp.getBody()).get("id").asLong();

        Thread.sleep(10000); // ☜(ˆ▿ˆc)👌

        resp = service.forwardGetOrder(orderId);
        Assertions.assertSame(HttpStatus.OK, resp.getStatusCode());
        var orderJson = mapper.readTree(resp.getBody());
        Assertions.assertEquals("FINISHED", orderJson.get("status").asText());
    }
}
