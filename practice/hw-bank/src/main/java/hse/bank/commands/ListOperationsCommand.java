package hse.bank.commands;

import hse.bank.domains.Operation;
import hse.bank.facades.OperationFacade;
import hse.bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListOperationsCommand implements Command<List<Operation>> {
    private final OperationFacade operations;

    @Override
    public List<Operation> execute() {
        return operations.getAllOperations();
    }
}
