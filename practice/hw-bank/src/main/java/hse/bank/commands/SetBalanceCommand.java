package hse.bank.commands;

import hse.bank.domains.BankAccount;
import hse.bank.facades.AccountFacade;
import hse.bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

/**
 * Command to set account balance to specified value
 */
@RequiredArgsConstructor
public class SetBalanceCommand implements Command<Void> {
    private final AccountFacade accounts;
    private final int accountId;
    private final double balance;

    @Override
    public Void execute() {
        BankAccount account = accounts.getAccount(accountId);
        account.setBalance(balance);
        accounts.saveAccount(account);
        return null;
    }
}
