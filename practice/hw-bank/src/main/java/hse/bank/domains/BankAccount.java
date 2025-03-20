package hse.bank.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class BankAccount {
    private final int id;
    private final String name;
    @Setter
    private double balance;

    public BankAccount(int id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0;
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }

}

