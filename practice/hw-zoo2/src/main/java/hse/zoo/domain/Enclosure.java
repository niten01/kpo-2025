package hse.zoo.domain;

import hse.zoo.domain.enums.EnclosureType;
import hse.zoo.domain.valueobjets.EnclosureID;
import hse.zoo.exceptions.ZooException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Slf4j
public class Enclosure {
    private final EnclosureID id;
    private final EnclosureType enclosureType;
    private final int maxCapacity;
    private final int volume;
    private final List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        if (animals.size() == maxCapacity) {
            throw new ZooException("Enclosure is full.");
        }

        animal.moveToEnclosure(this);
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animal.removeFromEnclosure();
        this.animals.remove(animal);
    }

    public void clean() {
        log.info("Enclosure cleaned.");
    }

}
