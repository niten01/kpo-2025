package hse.bank.persistence;

import hse.bank.domains.Operation;
import hse.bank.domains.OperationType;
import hse.bank.facades.OperationFacade;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class OperationYAMLSerializationService extends YamlSerializationService<Operation> {
    private final OperationFacade operationFacade;

    @Override
    protected Operation createFromMap(Map<String, Object> data) {
        return operationFacade.addOperation((Integer) data.get("id"), (Integer) data.get("account"), (Integer) data.get("category"), (OperationType) data.get("type"), (Double) data.get("amount"), (String) data.get("description"));
    }
}
