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
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CreateCategoryCommand implements Command<Void> {
    private final CategoryFacade categories;
    private final String name;
    private final OperationType type;

    @Override
    public Void execute() {
        categories.addCategory(name, type);
        return null;
    }
}

