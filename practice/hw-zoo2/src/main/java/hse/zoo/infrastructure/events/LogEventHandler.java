package hse.zoo.infrastructure.events;

import hse.zoo.domain.events.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogEventHandler implements EventHandler {
    @Override
    public void handle(Object event) {
        log.info("Handled event: {}", event);
    }
}
