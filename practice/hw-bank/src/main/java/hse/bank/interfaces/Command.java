package hse.bank.interfaces;

public interface Command<T> {
    T execute();
}
