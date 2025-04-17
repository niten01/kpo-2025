package hse.zoo.infrastructure.dto.converters;

import hse.zoo.domain.Enclosure;
import hse.zoo.domain.enums.EnclosureType;
import hse.zoo.domain.valueobjets.EnclosureID;
import hse.zoo.infrastructure.dto.EnclosureDTO;
import hse.zoo.infrastructure.dto.request.CreateEnclosureRequest;

public class EnclosureConverter {
    public static EnclosureDTO toDTO(Enclosure enclosure) {
        return new EnclosureDTO(enclosure.getId().value(), enclosure.getEnclosureType().toString(), enclosure.getMaxCapacity(), enclosure.getVolume());
    }

    public static Enclosure fromRequest(CreateEnclosureRequest request) {
        return new Enclosure(EnclosureID.create(), EnclosureType.valueOf(request.type), request.maxCapacity, request.volume);
    }
}
