package hse.bank.providers;

import hse.bank.domains.Operation;
import hse.bank.interfaces.Provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryOperationProvider implements Provider<Operation> {
    private final Map<UUID, Operation> operations = new HashMap<>();

    @Override
    public Optional<Operation> get(UUID id) {
        return Optional.ofNullable(operations.get(id));
    }

    @Override
    public void add(Operation operation) {
        operations.put(operation.getId(), operation);
    }

    @Override
    public void save(Operation operation) {
        operations.put(operation.getId(), operation);
    }

    @Override
    public void delete(UUID id) {
        operations.remove(id);
    }
}
