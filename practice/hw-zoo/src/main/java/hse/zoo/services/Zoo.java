package hse.zoo.services;

import hse.zoo.domains.Animal;
import hse.zoo.domains.Thing;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for zoo management
 */
@Component
public class Zoo {
    @Autowired
    private VetClinic vetClinic;
    @Getter
    private final List<Animal> animals = new ArrayList<>();
    @Getter
    private final List<Thing> things = new ArrayList<>();

    /**
     * adds new animal to the zoo
     * @param animal animal to add
     */
    public void addAnimal(Animal animal) {
        if (vetClinic.checkHealth(animal)) {
            animals.add(animal);
        }
    }


    /**
     * adds new thing to the zoo
     * @param thing thing to add
     */
    public void addThing(Thing thing) {
        things.add(thing);
    }
}