package hse.zoo.services;

import hse.zoo.domains.Animal;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Zoo {
    @Autowired
    private VetClinic vetClinic;
    @Getter
    private final List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        if (vetClinic.checkHealth(animal)) {
            animals.add(animal);
        }
    }
}