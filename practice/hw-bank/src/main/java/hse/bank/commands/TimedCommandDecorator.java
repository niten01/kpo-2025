package hse.bank.commands;

import hse.bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TimedCommandDecorator<T> implements Command<T> {
    private final Command<T> command;

    @Override
    public T execute() {
        long startTime = System.currentTimeMillis();
        T result = command.execute();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " ms for " + command.getClass().getSimpleName());
        return result;
    }
}
