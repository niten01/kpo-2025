package hse.zoo.factories;

import hse.zoo.domain.*;
import hse.zoo.params.AnimalParams;
import hse.zoo.params.HerboParams;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Creator of Animal objects
 */
@Component
public class AnimalFactory {
    private final Map<String, Class<? extends Animal>> animalTypes = Map.of("monkey", Monkey.class, "rabbit", Rabbit.class, "tiger", Tiger.class, "wolf", Wolf.class);

    /**
     * Creates new animal instance
     * @param type animal type string
     * @param food food consumption
     * @param kindness kindness level
     * @return created animal
     */
    public Animal createAnimal(String type, int food, Integer kindness) {
        Class<? extends Animal> animalClass = animalTypes.get(type.toLowerCase());
        if (animalClass == null) {
            throw new IllegalArgumentException("Invalid animal type.");
        }

        try {
            if (isHerbo(type)) {
                if (kindness == null || kindness < 0 || kindness > 10) {
                    throw new IllegalArgumentException("Invalid kindness level.");
                }
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