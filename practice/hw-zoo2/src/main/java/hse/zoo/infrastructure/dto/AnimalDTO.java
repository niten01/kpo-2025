package hse.zoo.infrastructure.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * Animal data transfer object
 */
@Getter
@RequiredArgsConstructor
public class AnimalDTO {
    private @NonNull String nickname;
    private @NonNull String species;
    private @NonNull Date birthDate;
    private @NonNull String gender;
    private @NonNull String status;
    private @NonNull String favoriteFood;
    private final Integer enclosureID;
}
