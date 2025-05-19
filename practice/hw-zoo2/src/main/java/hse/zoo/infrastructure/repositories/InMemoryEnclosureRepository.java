package hse.zoo.infrastructure.repositories;

import hse.zoo.application.interfaces.EnclosureRepository;
import hse.zoo.domain.Enclosure;
import hse.zoo.domain.valueobjets.EnclosureID;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryEnclosureRepository implements EnclosureRepository {
    private final List<Enclosure> enclosures = new ArrayList<>();

    @Override
    public void addEnclosure(Enclosure enclosure) {
        enclosures.add(enclosure);
    }

    @Override
    public void removeEnclosure(Enclosure enclosure) {
        enclosures.remove(enclosure);
    }

    @Override
    public List<Enclosure> getAllEnclosures() {
        return enclosures;
    }

    @Override
    public Optional<Enclosure> findEnclosure(EnclosureID id) {
        return enclosures.stream().filter(enclosure -> enclosure.getId().value() == id.value()).findFirst();
    }
}
