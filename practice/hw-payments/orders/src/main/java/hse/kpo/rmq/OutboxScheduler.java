package hse.kpo.rmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.OutboxEvent;
import hse.kpo.OutboxEventRepository;
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
public class OutboxScheduler {
    private final OutboxEventRepository outboxEventRepository;
    private final RabbitTemplate rabbitTemplate;

    @Value("${app.queues.payment-requests}")
    private String paymentRequestsQueue;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void publishEvents() {
        List<OutboxEvent> events = outboxEventRepository.findByProcessedAtIsNull();
        if (!events.isEmpty()) {
            log.info("Publishing {} payment request events", events.size());
        }
        for (OutboxEvent event : events) {
            try {
                rabbitTemplate.convertAndSend(paymentRequestsQueue, event.getPayload(), message -> {
                    message.getMessageProperties().setMessageId(event.getId().toString());
                    return message;
                });
                event.setProcessedAt(Instant.now());
                outboxEventRepository.save(event);
            } catch (Exception e) {
                log.error("Failed to publish: {}", event.getId(), e);
            }
        }
    }
}
