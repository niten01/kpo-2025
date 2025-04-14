package hse.bank.commands;

import hse.bank.domains.BankAccount;
import hse.bank.domains.Category;
import hse.bank.domains.Operation;
import hse.bank.domains.OperationType;
import hse.bank.facades.AccountFacade;
import hse.bank.facades.CategoryFacade;
import hse.bank.facades.OperationFacade;
import hse.bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class CreateOperationCommand implements Command<Void> {
    private final OperationFacade operations;
    private final int accountId;
    private final int categoryId;
    private final OperationType type;
    private final double amount;
    private final String description;

    @Override
    public Void execute() {
        operations.addOperation(accountId, categoryId, type, amount, new Date(), description);
        return null;
    }
}

