package hse.zoo.infrastructure.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * Shows necessary data for animal transfer domain event to happen
 */
public class MoveAnimalRequest {
    @NotBlank(message = "Animal nickname cannot be empty")
    @Schema(example = "Bobik")
    public String animalNickname;

    @NotBlank(message = "New enclosure ID cannot be empty")
    @Schema(example = "2")
    public Integer newEnclosureID;

}
