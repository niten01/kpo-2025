package hse.bank.persistence;

import hse.bank.domains.Operation;
import hse.bank.domains.OperationType;
import hse.bank.facades.OperationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class OperationCSVSerializationService extends CsvSerializerService<Operation> {
    private final OperationFacade operationFacade;

    @Override
    protected String serializeItem(Operation operation) {
        return operation.getId() + delimiter + operation.getBankAccountId() + delimiter + operation.getCategoryId() + delimiter + operation.getType() + delimiter + operation.getAmount() + delimiter + operation.getDate().getTime() + delimiter + operation.getDescription();
    }

    @Override
    protected Operation deserializeItem(String data) {
        String[] parts = data.split(delimiter);
        try {
            return operationFacade.addOperation(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), OperationType.valueOf(parts[3]), Double.parseDouble(parts[4]), new Date(Long.parseLong(parts[5])), parts.length > 6 ? parts[6] : "");
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new IllegalArgumentException("Invalid arguments for deserialization: " + e.getMessage());
        }
    }
}
