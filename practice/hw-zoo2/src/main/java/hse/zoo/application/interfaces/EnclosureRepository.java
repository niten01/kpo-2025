package hse.zoo.application.interfaces;

import hse.zoo.domain.Enclosure;
import hse.zoo.domain.valueobjets.EnclosureID;

import java.util.List;
import java.util.Optional;

public interface EnclosureRepository {
    void addEnclosure(Enclosure enclosure);

    void removeEnclosure(Enclosure enclosure);

    List<Enclosure> getAllEnclosures();

    Optional<Enclosure> findEnclosure(EnclosureID id);
}
