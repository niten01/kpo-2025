package hse.kpo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import hse.kpo.CreateOrderRequest;
import hse.kpo.services.OrdersService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService service;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@Valid @RequestBody CreateOrderRequest body) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(body));
    }

    @GetMapping("")
    public ResponseEntity<?> listOrders(@PathParam("user_id") Long user_id) {
        if (user_id == null) {
            throw new IllegalArgumentException("user_id is null");
        }
        return ResponseEntity.ok(service.filter(user_id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }
}
