package hse.bank.providers;

import hse.bank.domains.BankAccount;
import hse.bank.domains.Category;
import hse.bank.domains.Operation;
import hse.bank.interfaces.Provider;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * In-memory storage of bank accounts
 */
@Component
public class InMemoryAccountProvider implements Provider<BankAccount> {
    private final Map<Integer, BankAccount> accounts = new HashMap<>();

    @Override
    public List<BankAccount> getAll() {
        return accounts.values().stream().toList();
    }

    @Override
    public Optional<BankAccount> get(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    @Override
    public BankAccount add(BankAccount account) {
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public void save(BankAccount account) {
        if (get(account.getId()).isEmpty()) {
            throw new IllegalArgumentException("Account not found.");
        }
        accounts.put(account.getId(), account);
    }

    @Override
    public void delete(int id) {
        accounts.remove(id);
    }
}

