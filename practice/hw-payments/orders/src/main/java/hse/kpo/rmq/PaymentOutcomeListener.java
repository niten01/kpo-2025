package hse.kpo.rmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.PaymentOutcomeEvent;
import hse.kpo.services.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentOutcomeListener {
    private final OrdersService ordersService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${app.queues.payment-outcomes}")
    public void handlePaymentOutcome(String payload) {
        try {
            PaymentOutcomeEvent event = objectMapper.readValue(payload, PaymentOutcomeEvent.class);
            log.info("Received payment outcome event: {}", event);
            ordersService.updateStatus(event);
        } catch (Exception e) {
            log.error("Failed to handle payment outcome event", e);
        }
    }
}
