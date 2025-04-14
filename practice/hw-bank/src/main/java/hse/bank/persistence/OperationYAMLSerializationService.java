package hse.bank.persistence;

import hse.bank.domains.Operation;
import hse.bank.domains.OperationType;
import hse.bank.facades.OperationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OperationYAMLSerializationService extends YamlSerializationService<Operation> {
    private final OperationFacade operationFacade;

    @Override
    protected Operation createFromMap(Map<String, Object> data) {
        return operationFacade.addOperation((Integer) data.get("id"), (Integer) data.get("account"), (Integer) data.get("category"), OperationType.valueOf((String) data.get("type")), (Double) data.get("amount"), new Date((Integer) data.get("date")), (String) data.get("description"));
    }

    @Override
    protected Map<String, Object> createMap(Operation item) {
        return Map.of("id", item.getId(), "account", item.getBankAccountId(), "category", item.getCategoryId(), "type", item.getType().toString(), "amount", item.getAmount(), "date", item.getDate().getTime(), "description", item.getDescription());
    }
}
