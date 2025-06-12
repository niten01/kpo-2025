package hse.kpo.rmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.InboxEvent;
import hse.kpo.InboxEventRepository;
import hse.kpo.PaymentOutcomeEvent;
import hse.kpo.services.PaymentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentRequestListener {
    private final InboxEventRepository inboxEventRepository;

    @RabbitListener(queues = "${app.queues.payment-requests}")
    public void handlePaymentRequests(String payload, @Header(AmqpHeaders.MESSAGE_ID) String messageId) {
        log.info("Received payment request with ID {} and payload: {}", messageId, payload);
        UUID eventId;
        try {
            eventId = UUID.fromString(messageId);
        } catch (Exception e) {
            log.error("Invalid event ID", e);
            return;
        }

        if (inboxEventRepository.existsById(eventId)) {
            log.warn("Event with ID {} already processed. Skipping.", eventId);
            return;
        }

        InboxEvent inboxEvent = new InboxEvent();
        inboxEvent.setEventId(eventId);
        inboxEvent.setPayload(payload);
        inboxEvent.setRecievedAt(Instant.now());
        inboxEventRepository.save(inboxEvent);

    }
}
