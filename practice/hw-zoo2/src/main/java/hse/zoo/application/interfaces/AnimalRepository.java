package hse.zoo.application.interfaces;

import hse.zoo.domain.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository {
    void addAnimal(Animal animal);

    void removeAnimal(Animal animal);

    List<Animal> getAllAnimals();

    Optional<Animal> findAnimal(String nickname);
}
