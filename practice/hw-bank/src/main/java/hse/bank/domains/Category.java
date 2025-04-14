package hse.bank.domains;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class Category {
    private final int id;
    private final String name;
    private final OperationType type;

    public Category(int id, String name, OperationType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

}
