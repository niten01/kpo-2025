package hse.bank.commands;

import hse.bank.domains.BankAccount;
import hse.bank.domains.Category;
import hse.bank.domains.Operation;
import hse.bank.facades.AccountFacade;
import hse.bank.facades.CategoryFacade;
import hse.bank.facades.OperationFacade;
import hse.bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListAccountsCommand implements Command<List<BankAccount>> {
    private final AccountFacade accounts;

    @Override
    public List<BankAccount> execute() {
        return accounts.getAllAccounts();
    }
}
