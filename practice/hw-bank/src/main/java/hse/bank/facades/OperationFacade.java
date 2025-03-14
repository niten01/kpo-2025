package hse.bank.facades;

import hse.bank.domains.Operation;
import hse.bank.interfaces.Command;
import hse.zoo.factories.FinanceFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OperationFacade {
    private final List<Operation> operations = new ArrayList<>();

    public void addOperation(UUID accountId, UUID categoryId, double amount, String description) {
        Operation operation = FinanceFactory.createOperation(accountId, categoryId, amount, description);
        operations.add(operation);
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
