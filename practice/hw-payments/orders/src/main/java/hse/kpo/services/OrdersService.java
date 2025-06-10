package hse.kpo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.*;
import hse.kpo.enums.OrderStatus;
import hse.kpo.persistence.Order;
import hse.kpo.persistence.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {
    private final OrderRepository repository;
    private final OutboxEventRepository outboxRepository;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${remote.payments-service.base-url}")
    private String paymentsServiceBaseUrl;

    public Order create(CreateOrderRequest request) throws JsonProcessingException {
        Order order = new Order();
        order.setUserId(request.userId());
        order.setAmount(request.amount());
        order.setStatus(OrderStatus.NEW);
        order.setCreatedAt(Instant.now());
        order = repository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(order.getId(), order.getUserId(), order.getAmount(), order.getCreatedAt());
        String payload = objectMapper.writeValueAsString(event);

        OutboxEvent outboxEvent = new OutboxEvent();
        outboxEvent.setAggregateType("Order");
        outboxEvent.setAggregateId(order.getId().toString());
        outboxEvent.setEventType("ORDER_CREATED");
        outboxEvent.setPayload(payload);
        outboxEvent.setCreatedAt(Instant.now());
        outboxEvent.setProcessedAt(null);
        outboxRepository.save(outboxEvent);

        return order;
    }

    public void updateStatus(PaymentOutcomeEvent event) {
        Order order = repository.findById(event.orderId()).orElseThrow(() -> new EntityNotFoundException("Order with id " + event.orderId() + " not found"));
        order.setStatus(event.paymentSuccessful() ? OrderStatus.FINISHED : OrderStatus.CANCELED);
        repository.save(order);
    }

    public List<Order> filter(Long user_id) {
        return repository.findAll().stream().filter(order -> order.getUserId().equals(user_id)).toList();
    }

    public Order get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found"));
    }
}
