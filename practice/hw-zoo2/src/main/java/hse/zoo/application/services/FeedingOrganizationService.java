package hse.zoo.application.services;

import hse.zoo.application.interfaces.FeedingScheduleRepository;
import hse.zoo.domain.FeedingSchedule;
import hse.zoo.domain.events.EventHandler;
import hse.zoo.domain.events.FeedingTimeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class FeedingOrganizationService {
    private final FeedingScheduleRepository feedingScheduleRepository;
    private final EventHandler eventHandler;

    public void feedAnimals() {
        for (FeedingSchedule scheduleItem : feedingScheduleRepository.getAllScheduleItems()) {
            if (scheduleItem.getDate().before(new Date()) && !scheduleItem.isDone()) {
                scheduleItem.getAnimal().feed(scheduleItem.getFood());
                scheduleItem.setDone(true);
                eventHandler.handle(new FeedingTimeEvent(scheduleItem.getAnimal().getNickname(), scheduleItem.getDate(), scheduleItem.getFood()));
            }
        }
    }

    public void markUndone() {
        for (FeedingSchedule scheduleItem : feedingScheduleRepository.getAllScheduleItems()) {
            scheduleItem.setDone(false);
        }
    }
}
