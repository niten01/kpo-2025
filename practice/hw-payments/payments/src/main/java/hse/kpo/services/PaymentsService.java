package hse.kpo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.OrderCreatedEvent;
import hse.kpo.OutboxEvent;
import hse.kpo.OutboxEventRepository;
import hse.kpo.PaymentOutcomeEvent;
import hse.kpo.CreateAccountRequest;
import hse.kpo.TopupRequest;
import hse.kpo.persistence.Account;
import hse.kpo.persistence.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentsService {
    private final AccountRepository accountRepository;
    private final OutboxEventRepository outboxRepository;
    private final ObjectMapper objectMapper;

    public Account create(CreateAccountRequest request) {
        if (accountRepository.existsById(request.userId())) {
            throw new IllegalArgumentException("User with id " + request.userId() + " already has an account.");
        }
        Account account = new Account();
        account.setId(request.userId());
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    public Account get(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account for user " + id + " not created"));
    }

    public Account topup(Long id, TopupRequest request) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account for user " + id + " not created"));
        account.setBalance(account.getBalance().add(request.amount()));
        return accountRepository.save(account);

    }

    @Transactional
    public void processPayment(OrderCreatedEvent event) {
        Account account = accountRepository.findById(event.userId()).orElse(null);

        boolean success = true;
        if (account == null) {
            success = false;
            log.warn("Account for user {} not created", event.userId());
        } else if (account.getBalance().compareTo(event.amount()) < 0) {
            success = false;
        } else {
            account.setBalance(account.getBalance().subtract(event.amount()));
            accountRepository.save(account);
        }

        PaymentOutcomeEvent outcomeEvent = new PaymentOutcomeEvent(event.orderId(), success, Instant.now());
        String payload;
        try {
            payload = objectMapper.writeValueAsString(outcomeEvent);
        } catch (Exception e) {
            log.error("Failed to serialize payment outcome event", e);
            return;
        }

        OutboxEvent outboxEvent = new OutboxEvent();
        outboxEvent.setAggregateType("Order");
        outboxEvent.setAggregateId(event.orderId().toString());
        outboxEvent.setEventType("PAYMENT_OUTCOME");
        outboxEvent.setPayload(payload);
        outboxEvent.setCreatedAt(Instant.now());
        outboxEvent.setProcessedAt(null);
        outboxRepository.save(outboxEvent);

    }

}
