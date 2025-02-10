package hse.zoo.factories;

import hse.zoo.domains.*;
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
                return animalClass.getConstructor(int.class, int.class).newInstance(food, kindness);
            } else {
                return animalClass.getConstructor(int.class).newInstance(food);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create animal instance", e);
        }
    }

    public boolean isHerbo(String type) {
        return Herbo.class.isAssignableFrom(animalTypes.get(type.toLowerCase()));
    }
}