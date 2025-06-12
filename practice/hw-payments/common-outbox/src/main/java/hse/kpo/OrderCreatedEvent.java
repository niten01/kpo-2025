package hse.kpo;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderCreatedEvent(Long orderId, Long userId, BigDecimal amount, Instant createdAt) {

}
