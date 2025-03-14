package hse.bank.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class BankAccount {
    private final UUID id;
    private final String name;
    @Setter
    private double balance;

    public BankAccount(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.balance = 0;
    }

}

