package hse.bank.commands;

import hse.bank.domains.BankAccount;
import hse.bank.domains.Category;
import hse.bank.domains.Operation;
import hse.bank.facades.AccountFacade;
import hse.bank.facades.CategoryFacade;
import hse.bank.facades.OperationFacade;
import hse.bank.interfaces.Command;
import hse.bank.persistence.AbstractSerializationService;
import hse.bank.persistence.BankAccountYAMLSerializationService;
import hse.bank.persistence.CategoryYAMLSerializationService;
import hse.bank.persistence.OperationYAMLSerializationService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class ExportCommand implements Command<Void> {
    private final AccountFacade accountFacade;
    private final CategoryFacade categoryFacade;
    private final OperationFacade operationFacade;
    private final AbstractSerializationService<BankAccount> bankAccountYAMLSerializationService;
    private final AbstractSerializationService<Category> categoryYAMLSerializationService;
    private final AbstractSerializationService<Operation> operationYAMLSerializationService;

    @Override
    public Void execute() {
        try {
            bankAccountYAMLSerializationService.serialize(accountFacade.getAllAccounts(), "accounts.yml");
            categoryYAMLSerializationService.serialize(categoryFacade.getAllCategories(), "categories.yml");
            operationYAMLSerializationService.serialize(operationFacade.getAllOperations(), "operations.yml");
        } catch (IOException e) {
            throw new IllegalArgumentException("Serialization failure: " + e.getMessage());
        }
        return null;
    }
}
