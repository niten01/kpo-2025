package hse.bank.domains;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Category {
    private final UUID id;
    private final String name;
    private final OperationType type;

    public Category(String name, OperationType type) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.type = type;
    }

}
