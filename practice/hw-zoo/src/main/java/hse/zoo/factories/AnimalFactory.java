package hse.zoo.factories;

import hse.zoo.domains.*;
import hse.zoo.params.AnimalParams;
import hse.zoo.params.HerboParams;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AnimalFactory {
    private final Map<String, Class<? extends Animal>> animalTypes = Map.of("monkey", Monkey.class, "rabbit", Rabbit.class, "tiger", Tiger.class, "wolf", Wolf.class);

    public Animal createAnimal(String type, int food, Integer kindness) {
        Class<? extends Animal> animalClass = animalTypes.get(type.toLowerCase());
        if (animalClass == null) {
            throw new IllegalArgumentException("Invalid animal type.");
        }

        try {
            if (isHerbo(type)) {
                return animalClass.getConstructor(HerboParams.class).newInstance(new HerboParams(food, kindness));
            } else {
                return animalClass.getConstructor(AnimalParams.class).newInstance(new AnimalParams(food));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create animal instance", e);
        }
    }

    public boolean isHerbo(String type) {
        Class<? extends Animal> animalClass = animalTypes.get(type.toLowerCase());
        if (animalClass == null) {
            throw new IllegalArgumentException("Invalid animal type.");
        }

        return Herbo.class.isAssignableFrom(animalClass);
    }
}