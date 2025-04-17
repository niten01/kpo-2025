package hse.zoo.application.interfaces;

import hse.zoo.domain.FeedingSchedule;

import java.util.List;

public interface FeedingScheduleRepository {
    void addScheduleItem(FeedingSchedule scheduleItem);

    void removeScheduleItem(FeedingSchedule scheduleItem);

    List<FeedingSchedule> getAllScheduleItems();
}
