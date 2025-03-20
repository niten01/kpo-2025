package hse.bank.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface for storing domain objects
 * @param <T> object type
 */
public interface Provider<T> {
    List<T> getAll();

    Optional<T> get(int id);

    T add(T t);

    void save(T t);

    void delete(int id);
}
