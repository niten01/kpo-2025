package hse.kpo.controllers;

import hse.kpo.CreateAccountRequest;
import hse.kpo.TopupRequest;
import hse.kpo.services.PaymentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentsController {
    private final PaymentsService service;

    @PostMapping("/accounts/create")
    public ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id)  {
        return ResponseEntity.ok().body(service.get(id));
    }

    @PostMapping("/accounts/{id}/topup")
    public ResponseEntity<?> topupAccount(@Valid @RequestBody TopupRequest request, @PathVariable Long id) {
        log.info("Topup request: {}", request);
        return ResponseEntity.ok().body(service.topup(id, request));
    }

}
