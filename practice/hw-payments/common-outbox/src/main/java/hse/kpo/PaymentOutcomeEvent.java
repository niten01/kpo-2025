package hse.kpo;

import java.time.Instant;

public record PaymentOutcomeEvent(Long orderId, Boolean paymentSuccessful, Instant processedAt) {
}
