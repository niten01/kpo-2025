package hse.zoo.domain.events;

import hse.zoo.domain.valueobjets.EnclosureID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class AnimalMovedEvent {
    private final String animalNickname;
    private final EnclosureID oldEnclosureId;
    private final EnclosureID newEnclosureId;
}
