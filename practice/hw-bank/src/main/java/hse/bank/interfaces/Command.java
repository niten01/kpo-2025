package hse.bank.interfaces;

/**
 * Command interface
 * @param <T> return type
 */
public interface Command<T> {
    T execute();
}
