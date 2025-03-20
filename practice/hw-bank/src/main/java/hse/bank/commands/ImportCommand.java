package hse.bank.commands;

import hse.bank.domains.BankAccount;
import hse.bank.domains.Category;
import hse.bank.domains.Operation;
import hse.bank.interfaces.Command;
import hse.bank.persistence.AbstractSerializationService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class ImportCommand implements Command<Void> {
    private final AbstractSerializationService<BankAccount> bankAccountYAMLSerializationService;
    private final AbstractSerializationService<Category> categoryYAMLSerializationService;
    private final AbstractSerializationService<Operation> operationYAMLSerializationService;

    @Override
    public Void execute() {
        try {
            bankAccountYAMLSerializationService.deserialize("accounts.yml");
            categoryYAMLSerializationService.deserialize("categories.yml");
            operationYAMLSerializationService.deserialize("operations.yml");
        } catch (IOException e) {
            throw new IllegalArgumentException("Deserialization failure: " + e.getMessage());
        }
        return null;
    }
}
