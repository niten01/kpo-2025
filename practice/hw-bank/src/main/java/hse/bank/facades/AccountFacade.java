package hse.bank.facades;

import hse.bank.domains.BankAccount;
import hse.bank.interfaces.Provider;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class AccountFacade {
    private final Provider<BankAccount> accountsProvider;

    public BankAccount getAccount(UUID id) {
        var acc = accountsProvider.get(id);
        if (acc.isEmpty()) {
            throw new IllegalArgumentException("Account not found.");
        }
        return acc.get();
    }

    public void addAccount(String name) {
    }
}
