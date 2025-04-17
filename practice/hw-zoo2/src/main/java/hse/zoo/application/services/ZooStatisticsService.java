package hse.zoo.application.services;

import hse.zoo.application.interfaces.AnimalRepository;
import hse.zoo.application.interfaces.EnclosureRepository;
import hse.zoo.domain.enums.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ZooStatisticsService {
    private final AnimalRepository animalRepository;
    private final EnclosureRepository enclosureRepository;

    public int countAnimalsByGender(Gender gender) {
        return animalRepository.getAllAnimals().stream().filter(animal -> animal.getGender() == gender).toList().size();
    }

    public int countAnimalsWithoutEnclosure() {
        return animalRepository.getAllAnimals().stream().filter(animal -> animal.getEnclosure() == null).toList().size();
    }

    public int countEmptyEnclosures() {
        return enclosureRepository.getAllEnclosures().stream().filter(enclosure -> enclosure.getAnimals().isEmpty()).toList().size();
    }
}
