package hse.zoo.application.services;

import hse.zoo.application.interfaces.AnimalRepository;
import hse.zoo.application.interfaces.EnclosureRepository;
import hse.zoo.domain.Enclosure;
import hse.zoo.domain.events.AnimalMovedEvent;
import hse.zoo.domain.events.EventHandler;
import hse.zoo.domain.valueobjets.EnclosureID;
import hse.zoo.exceptions.ZooException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnimalTransferService {
    private final EventHandler eventHandler;
    private final EnclosureRepository enclosureRepository;
    private final AnimalRepository animalRepository;

    public void transferAnimal(String animalNickname, EnclosureID newEnclosureID) {
        var animal = animalRepository.findAnimal(animalNickname).orElseThrow(() -> new ZooException("Animal not found"));

        Enclosure oldEnclosure = null;
        if (animal.getEnclosure() != null) {
            oldEnclosure = enclosureRepository.findEnclosure(animal.getEnclosure().getId()).orElseThrow(() -> new ZooException("Old enclosure not found"));
        }

        var newEnclosure = enclosureRepository.findEnclosure(newEnclosureID).orElseThrow(() -> new ZooException("New enclosure not found"));

        if (oldEnclosure != null) {
            oldEnclosure.removeAnimal(animal);
        }
        newEnclosure.addAnimal(animal);
        eventHandler.handle(new AnimalMovedEvent(animalNickname, oldEnclosure != null ? oldEnclosure.getId() : null, newEnclosureID));
    }
}
