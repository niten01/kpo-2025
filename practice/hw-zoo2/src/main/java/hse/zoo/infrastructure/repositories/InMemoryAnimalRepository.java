package hse.zoo.infrastructure.repositories;

import hse.zoo.application.interfaces.AnimalRepository;
import hse.zoo.domain.Animal;
import hse.zoo.exceptions.ZooException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryAnimalRepository implements AnimalRepository {
    private final List<Animal> animals = new ArrayList<>();

    @Override
    public void addAnimal(Animal animal) {
        if (!findAnimal(animal.getNickname()).isEmpty()) {
            throw new ZooException("Animal with nickname " + animal.getNickname() + " already exists");
        }
        animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    @Override
    public List<Animal> getAllAnimals() {
        return animals;
    }

    @Override
    public Optional<Animal> findAnimal(String nickname) {
        return animals.stream().filter(animal -> animal.getNickname().equals(nickname)).findFirst();
    }
}
