package hse.zoo.services;

import hse.zoo.domains.Animal;
import hse.zoo.domains.Thing;
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
    @Getter
    private final List<Thing> things = new ArrayList<>();

    public void addAnimal(Animal animal) {
        if (vetClinic.checkHealth(animal)) {
            animals.add(animal);
        }
    }

    public void addThing(Thing thing) {
        things.add(thing);
    }
}