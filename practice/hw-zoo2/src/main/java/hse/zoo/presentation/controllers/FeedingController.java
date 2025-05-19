package hse.zoo.presentation.controllers;

import hse.zoo.ZooApplication;
import hse.zoo.application.interfaces.AnimalRepository;
import hse.zoo.application.interfaces.FeedingScheduleRepository;
import hse.zoo.application.services.FeedingOrganizationService;
import hse.zoo.exceptions.ZooException;
import hse.zoo.infrastructure.dto.FeedingScheduleDTO;
import hse.zoo.infrastructure.dto.converters.FeedingScheduleConverter;
import hse.zoo.infrastructure.dto.request.CreateFeedingScheduleRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/feeding")
@RequiredArgsConstructor
public class FeedingController {
    private final FeedingScheduleRepository feedingScheduleRepository;
    private final AnimalRepository animalRepository;
    private final FeedingOrganizationService feedingOrganizationService;


    @GetMapping
    public List<FeedingScheduleDTO> getAllFeedings() {
        return feedingScheduleRepository.getAllScheduleItems().stream().map(FeedingScheduleConverter::toDTO).toList();
    }

    @PostMapping("/feed")
    public void feedAnimals() {
        try {
            feedingOrganizationService.feedAnimals();
        } catch (ZooException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping
    public FeedingScheduleDTO createFeeding(@Valid @RequestBody CreateFeedingScheduleRequest request) {
        try {
            var schedule = FeedingScheduleConverter.fromRequest(request, animalRepository);
            feedingScheduleRepository.addScheduleItem(schedule);
            return FeedingScheduleConverter.toDTO(schedule);
        } catch (ZooException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

    }

}
