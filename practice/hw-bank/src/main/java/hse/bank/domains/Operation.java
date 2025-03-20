package hse.bank.domains;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
@ToString
public class Operation {
    private final int id;
    private final int bankAccountId;
    private final int categoryId;
    private final OperationType type;
    private final double amount;
    private final Date date;
    private final String description;

    public double getSignedAmount() {
        return type == OperationType.INCOME ? amount : -amount;
    }
}
