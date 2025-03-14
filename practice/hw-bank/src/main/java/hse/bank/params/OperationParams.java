package hse.bank.params;

import hse.bank.domains.OperationType;

import java.util.Date;
import java.util.UUID;

/**
 * Operation creation params
 * @param bankAccountId account this operation belongs to
 * @param categoryId category this operation belongs to
 * @param amount operation amount
 * @param type operation type
 * @param date operation date
 * @param description operation description (may be empty)
 */
public record OperationParams(UUID bankAccountId, UUID categoryId, double amount, OperationType type, Date date,
                              String description) {
}
