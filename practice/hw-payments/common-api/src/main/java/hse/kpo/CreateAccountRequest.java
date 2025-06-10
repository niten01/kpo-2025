package hse.kpo;

import jakarta.validation.constraints.NotNull;

public record CreateAccountRequest(@NotNull Long userId) {
}
