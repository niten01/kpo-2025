package hse.bank.interfaces;

import java.util.Optional;
import java.util.UUID;

public interface Provider<T> {
    Optional<T> get(UUID id);

    void add(T t);

    void save(T t);

    void delete(UUID id);
}
