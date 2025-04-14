package hse.bank.facades;

import hse.bank.domains.BankAccount;
import hse.bank.domains.Operation;
import hse.bank.interfaces.Provider;
import hse.bank.factories.FinanceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Bank account management service
 */
@Component
@RequiredArgsConstructor
public class AccountFacade {
    private final Provider<BankAccount> accountsProvider;
    private final OperationFacade operationFacade;
    private static int defaultIDCounter = 0;

    public BankAccount getAccount(int id) {
        var acc = accountsProvider.get(id);
        if (acc.isEmpty()) {
            throw new IllegalArgumentException("Account not found.");
        }
        return acc.get();
    }

    public List<BankAccount> getAllAccounts() {
        return new ArrayList<>(accountsProvider.getAll());
    }

    public BankAccount addAccount(Integer id, String name) {
        return accountsProvider.add(FinanceFactory.createBankAccount(id, name));
    }

    public BankAccount addAccount(String name) {
        return addAccount(defaultIDCounter++, name);
    }

    public void saveAccount(BankAccount account) {
        accountsProvider.save(account);
    }

    public void deleteAccount(BankAccount account) {
        accountsProvider.delete(account.getId());
    }

    public void fixBalances() {
        List<BankAccount> accounts = accountsProvider.getAll();
        for (BankAccount account : accounts) {
            account.setBalance(0);
            for (Operation operation : operationFacade.getAllOperations().stream().filter(op -> op.getBankAccountId() == account.getId()).toList()) {
                account.updateBalance(operation.getSignedAmount());
            }
            saveAccount(account);
        }
    }
}
