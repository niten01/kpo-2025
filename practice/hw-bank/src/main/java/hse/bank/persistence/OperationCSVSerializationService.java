package hse.bank.persistence;

import hse.bank.domains.Operation;
import hse.bank.domains.OperationType;
import hse.bank.facades.OperationFacade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OperationCSVSerializationService extends AbstractSerializationService<Operation> {
    private final String delimiter;
    private final OperationFacade operationFacade;

    @Override
    protected String serializeItem(Operation operation) {
        return operation.getId() + delimiter + operation.getBankAccountId() + delimiter + operation.getCategoryId() + delimiter + operation.getType() + delimiter + operation.getAmount() + delimiter + operation.getDescription();
    }

    @Override
    protected Operation deserializeItem(String data) {
        String[] parts = data.split(delimiter);
        try {
            return operationFacade.addOperation(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), OperationType.valueOf(parts[2]), Double.parseDouble(parts[3]), parts.length > 4 ? parts[4] : "");
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new IllegalArgumentException("Invalid arguments for deserialization: " + e.getMessage());
        }
    }
}
