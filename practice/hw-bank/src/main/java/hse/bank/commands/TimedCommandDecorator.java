package hse.bank.commands;

import hse.bank.interfaces.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class TimedCommandDecorator<T> implements Command<T> {
    private final Command<T> command;

    @Override
    public T execute() {
        long startTime = System.nanoTime();
        T result = command.execute();
        long endTime = System.nanoTime();
        log.info("Execution time: {} ms for {}", String.format("%.2f", (endTime - startTime) / 1e6), command.getClass().getSimpleName());
        return result;
    }
}
