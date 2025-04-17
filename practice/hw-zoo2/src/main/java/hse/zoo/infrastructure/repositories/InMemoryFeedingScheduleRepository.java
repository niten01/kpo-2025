package hse.zoo.infrastructure.repositories;

import hse.zoo.application.interfaces.FeedingScheduleRepository;
import hse.zoo.domain.FeedingSchedule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryFeedingScheduleRepository implements FeedingScheduleRepository {
    private final List<FeedingSchedule> scheduleItems = new ArrayList<>();

    @Override
    public void addScheduleItem(FeedingSchedule scheduleItem) {
        scheduleItems.add(scheduleItem);
    }

    @Override
    public void removeScheduleItem(FeedingSchedule scheduleItem) {
        scheduleItems.remove(scheduleItem);
    }

    @Override
    public List<FeedingSchedule> getAllScheduleItems() {
        return scheduleItems;
    }
}
