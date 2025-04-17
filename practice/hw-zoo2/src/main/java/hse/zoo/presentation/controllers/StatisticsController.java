package hse.zoo.presentation.controllers;

import hse.zoo.application.services.ZooStatisticsService;
import hse.zoo.domain.enums.Gender;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final ZooStatisticsService zooStatisticsService;

    @GetMapping("/count_by_gender/{gender}")
    public int countAnimalsByGender(@PathVariable String gender) {
        try {
            return zooStatisticsService.countAnimalsByGender(Gender.valueOf(gender));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gender must be either MALE or FEMALE");
        }
    }

    @GetMapping("/count_without_enclosure")
    public int countAnimalsWithoutEnclosure() {
        return zooStatisticsService.countAnimalsWithoutEnclosure();
    }

    @GetMapping("/count_empty_enclosures")
    public int countEmptyEnclosures() {
        return zooStatisticsService.countEmptyEnclosures();
    }
}
