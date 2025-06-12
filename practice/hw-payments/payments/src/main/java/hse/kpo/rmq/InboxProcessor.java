package hse.kpo.rmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.*;
import hse.kpo.services.PaymentsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InboxProcessor {
    private final InboxEventRepository inboxEventRepository;
    private final PaymentsService paymentsService;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void processInbox() {
        List<InboxEvent> events = inboxEventRepository.findByProcessedAtIsNull();
        for (InboxEvent event : events) {
            try {
                OrderCreatedEvent orderEvent = objectMapper.readValue(event.getPayload(), OrderCreatedEvent.class);
                paymentsService.processPayment(orderEvent);
                event.setProcessedAt(Instant.now());
                inboxEventRepository.save(event);
            } catch (Exception e) {
                log.error("Failed to process inbox event {}", event.getEventId(), e);
            }
        }
    }
}

