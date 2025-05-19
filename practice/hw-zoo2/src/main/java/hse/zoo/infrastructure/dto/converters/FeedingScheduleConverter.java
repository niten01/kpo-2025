package hse.zoo.infrastructure.dto.converters;

import hse.zoo.application.interfaces.AnimalRepository;
import hse.zoo.domain.FeedingSchedule;
import hse.zoo.domain.valueobjets.Food;
import hse.zoo.exceptions.ZooException;
import hse.zoo.infrastructure.dto.FeedingScheduleDTO;
import hse.zoo.infrastructure.dto.request.CreateFeedingScheduleRequest;

public class FeedingScheduleConverter {
    public static FeedingSchedule fromRequest(CreateFeedingScheduleRequest request, AnimalRepository animalRepository) {
        return new FeedingSchedule(animalRepository.findAnimal(request.animalNickname).orElseThrow(() -> new ZooException("Animal not found")), Food.create(request.food), request.date);
    }

    public static FeedingScheduleDTO toDTO(FeedingSchedule schedule) {
        return new FeedingScheduleDTO(schedule.getAnimal().getNickname(), schedule.getFood().name(), schedule.getDate());
    }
}
