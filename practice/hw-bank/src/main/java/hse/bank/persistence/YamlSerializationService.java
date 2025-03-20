package hse.bank.persistence;

import hse.bank.domains.BankAccount;
import hse.bank.domains.Category;
import hse.bank.domains.Operation;
import hse.bank.domains.OperationType;
import hse.bank.facades.AccountFacade;
import hse.bank.facades.CategoryFacade;
import hse.bank.facades.OperationFacade;
import lombok.RequiredArgsConstructor;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

public abstract class YamlSerializationService<T> extends AbstractSerializationService<T> {
    private final Yaml yaml = new Yaml();

    @Override
    protected String serializeItem(T item) {
        return yaml.dump(item);
    }

    @Override
    protected T deserializeItem(String data) {
        Map<String, Object> map = yaml.load(data);
        return createFromMap(map);
    }

    protected abstract T createFromMap(Map<String, Object> data);
}

