package hse.kpo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import hse.kpo.CreateOrderRequest;
import hse.kpo.TopupRequest;
import hse.kpo.services.ProxyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Gateway API", description = "Proxy for shopping")
public class ApiController {
    @Autowired
    private ProxyService proxyService;

    @PostMapping("/users/{uid}/accounts/create")
    @Operation(summary = "Create payment account", description = "Forwards account creation request to payments service")
    public ResponseEntity<String> createAccount(@PathVariable Long uid) {
        return proxyService.forwardCreateAccount(uid);
    }

    @PostMapping("/users/{uid}/accounts/topup")
    @Operation(summary = "Add funds to payment account", description = "Forwards funds addition request to payments service")
    public ResponseEntity<String> topup(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopupRequest.class))) String body, @PathVariable Long uid) {
        return proxyService.forwardTopup(body, uid);
    }

    @GetMapping("/users/{uid}/account")
    @Operation(summary = "Get payment account info", description = "Fetch payment account by ID")
    public ResponseEntity<String> getAccount(@PathVariable Long uid) {
        return proxyService.forwardGetAccount(uid);
    }

    @PostMapping("/orders/create")
    @Operation(summary = "Create order", description = "Forwards order creation request to orders service")
    public ResponseEntity<String> createOrder(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateOrderRequest.class))) String body) throws JsonProcessingException {
        return proxyService.forwardCreateOrder(body);
    }

    @GetMapping("/users/{uid}/orders")
    @Operation(summary = "List user's orders", description = "Returns all orders of user")
    public ResponseEntity<String> listOrders(@PathVariable Long uid) {
        return proxyService.forwardListOrders(uid);
    }

    @GetMapping("/orders/{id}")
    @Operation(summary = "Get order info", description = "Fetch order by ID")
    public ResponseEntity<String> getOrder(@PathVariable Long id) {
        return proxyService.forwardGetOrder(id);
    }
}
