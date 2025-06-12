package hse.kpo;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateOrderRequest(@NotNull Long userId, @NotNull @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be positive") BigDecimal amount) {
}
