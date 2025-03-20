package hse.bank.providers;

import hse.bank.domains.Operation;
import hse.bank.interfaces.Provider;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * In-memory storage of operations
 */
@Component
public class InMemoryOperationProvider implements Provider<Operation> {
    private final Map<Integer, Operation> operations = new HashMap<>();

    @Override
    public List<Operation> getAll() {
        return operations.values().stream().toList();
    }

    @Override
    public Optional<Operation> get(int id) {
        return Optional.ofNullable(operations.get(id));
    }

    @Override
    public Operation add(Operation operation) {
        operations.put(operation.getId(), operation);
        return operation;
    }

    @Override
    public void save(Operation operation) {
        if (get(operation.getId()).isEmpty()) {
            throw new IllegalArgumentException("Operation not found.");
        }
        operations.put(operation.getId(), operation);
    }

    @Override
    public void delete(int id) {
        operations.remove(id);
    }
}
