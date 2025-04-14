package hse.bank.commands;

import hse.bank.facades.AccountFacade;
import hse.bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

/**
 * Command to fix account balances by operations
 */
@RequiredArgsConstructor
public class FixBalanceCommand implements Command<Void> {
    private final AccountFacade accounts;

    @Override
    public Void execute() {
        accounts.fixBalances();
        return null;
    }
}
