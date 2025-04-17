package hse.zoo.infrastructure.dto.request;


import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

/**
 * Shows what data is needed to create an animal (and it's constraints)
 */
public class CreateAnimalRequest {
    @NotBlank(message = "Nickname cannot be empty")
    @Schema(example = "Bobik")
    public String nickname;

    @NotBlank(message = "Species cannot be empty")
    @Schema(example = "Siren intermedia")
    public String species;

    @NotBlank(message = "Birth date cannot be empty")
    @Schema(example = "2022-12-12")
    public Date birthDate;

    @NotBlank(message = "Gender cannot be empty")
    @Schema(example = "MALE")
    @Pattern(regexp = "MALE|FEMALE", message = "Gender must be either MALE or FEMALE")
    public String gender;

    @NotBlank(message = "Favourite food cannot be empty")
    @Schema(example = "Meat")
    public String favouriteFood;

    @NotBlank(message = "Status cannot be empty")
    @Schema(example = "HEALTHY")
    @Pattern(regexp = "HEALTHY|SICK", message = "Status must be either HEALTHY or SICK")
    public String status;
}
