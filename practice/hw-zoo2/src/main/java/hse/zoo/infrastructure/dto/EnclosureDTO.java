package hse.zoo.infrastructure.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Enclosure data transfer object
 */
@Getter
@RequiredArgsConstructor
public class EnclosureDTO {
    private @NonNull Integer id;
    private @NonNull String type;
    private @NonNull Integer maxCapacity;
    private @NonNull Integer volume;
}
