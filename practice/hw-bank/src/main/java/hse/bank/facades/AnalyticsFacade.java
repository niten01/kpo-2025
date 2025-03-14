package hse.bank.facades;

import hse.bank.domains.Operation;
import hse.bank.domains.OperationType;

public class AnalyticsFacade {
    private final OperationFacade operationFacade;

    public AnalyticsFacade(OperationFacade operationFacade) {
        this.operationFacade = operationFacade;
    }

    public double calculateBalanceDifference() {
        double income = operationFacade.getOperations().stream().filter(op -> op.getType() == OperationType.INCOME).mapToDouble(Operation::getAmount).sum();
        double expense = operationFacade.getOperations().stream().filter(op -> op.getType() == OperationType.EXPENSE).mapToDouble(Operation::getAmount).sum();
        return income - expense;
    }
}
