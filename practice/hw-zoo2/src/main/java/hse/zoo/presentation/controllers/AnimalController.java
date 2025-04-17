package hse.zoo.presentation.controllers;

import hse.zoo.application.interfaces.AnimalRepository;
import hse.zoo.domain.Animal;
import hse.zoo.exceptions.ZooException;
import hse.zoo.infrastructure.dto.AnimalDTO;
import hse.zoo.infrastructure.dto.converters.AnimalConverter;
import hse.zoo.infrastructure.dto.request.CreateAnimalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalRepository animalRepository;

    @GetMapping
    public List<AnimalDTO> getAllAnimals() {
        return animalRepository.getAllAnimals().stream().map(AnimalConverter::toDTO).toList();
    }

    @GetMapping("/{nickname}")
    public AnimalDTO getAnimal(@PathVariable String nickname) {
        Animal animal = animalRepository.findAnimal(nickname).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found"));
        return AnimalConverter.toDTO(animal);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalDTO createAnimal(@Valid @RequestBody CreateAnimalRequest request) {
        Animal newAnimal = AnimalConverter.fromRequest(request);
        try {
            animalRepository.addAnimal(newAnimal);
        } catch (ZooException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
        return AnimalConverter.toDTO(newAnimal);
    }

    @DeleteMapping("/{nickname}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnimal(@PathVariable String nickname) {
        Animal animal = animalRepository.findAnimal(nickname).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found"));
        animalRepository.removeAnimal(animal);
    }

}
