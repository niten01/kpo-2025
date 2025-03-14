package hse.bank.providers;

import hse.bank.domains.BankAccount;
import hse.bank.domains.Category;
import hse.bank.domains.Operation;
import hse.bank.interfaces.Provider;

import java.util.*;

public class InMemoryAccountProvider implements Provider<BankAccount> {
    private final Map<UUID, BankAccount> accounts = new HashMap<>();

    @Override
    public Optional<BankAccount> get(UUID id) {
        return Optional.ofNullable(accounts.get(id));
    }

    @Override
    public void add(BankAccount account) {
        accounts.put(account.getId(), account);
    }

    @Override
    public void save(BankAccount account) {
        if (get(account.getId()).isEmpty()) {
            throw new IllegalArgumentException("Account not found.");
        }
        accounts.put(account.getId(), account);
    }

    @Override
    public void delete(UUID id) {
        accounts.remove(id);
    }
}

