package hse.kpo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import hse.kpo.CreateOrderRequest;
import hse.kpo.TopupRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class ProxyService {

    @Value("${remote.orders-service.base-url}")
    private String ordersServiceBaseUrl;
    @Value("${remote.payments-service.base-url}")
    private String paymentsServiceBaseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> forwardCreateAccount(Long uid) {
        String url = paymentsServiceBaseUrl + "/api/accounts/create";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.postForEntity(url, new HttpEntity<>("{\"userId\": " + uid + "}", headers), String.class);
    }

    public ResponseEntity<String> forwardTopup(String body, Long id) {
        String url = paymentsServiceBaseUrl + "/api/accounts/" + id + "/topup";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.postForEntity(url, new HttpEntity<>(body, headers), String.class);
    }

    public ResponseEntity<String> forwardGetAccount(Long id) {
        String url = paymentsServiceBaseUrl + "/api/accounts/" + id;
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> forwardCreateOrder(String body) throws JsonProcessingException {
        String url = ordersServiceBaseUrl + "/api/orders/create";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.postForEntity(url, new HttpEntity<>(body, headers), String.class);
    }

    public ResponseEntity<String> forwardListOrders(Long id) {
        String url = ordersServiceBaseUrl + "/api/orders?user_id=" + id;
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> forwardGetOrder(Long id) {
        String url = ordersServiceBaseUrl + "/api/orders/" + id;
        return restTemplate.getForEntity(url, String.class);
    }
}
