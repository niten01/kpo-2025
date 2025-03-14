package hse.bank.domains;

import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class Operation {
    private final UUID id;
    private final UUID bankAccountId;
    private final UUID categoryId;
    private final double amount;
    private final Date date;
    private final String description;
    private final OperationType type;

    public Operation(UUID bankAccountId, UUID categoryId, OperationType type, double amount, Date date, String description) {
        this.id = UUID.randomUUID();
        this.bankAccountId = bankAccountId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.type = type;
    }
}
