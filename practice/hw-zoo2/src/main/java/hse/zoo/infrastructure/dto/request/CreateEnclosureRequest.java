package hse.zoo.infrastructure.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Shows what data is needed to create an enclosure (and it's constraints)
 */
public class CreateEnclosureRequest {
    @NotBlank(message = "Type cannot be empty")
    @Schema(example = "CAGE")
    @Pattern(regexp = "CAGE|TERRARIUM|AQUARIUM", message = "Type must be either CAGE, TERRARIUM or AQUARIUM")
    public String type;

    @NotBlank(message = "Max capacity cannot be empty")
    @Schema(example = "10")
    @Min(value = 1, message = "Max capacity must be at least 1")
    public int maxCapacity;

    @NotBlank(message = "Volume cannot be empty")
    @Schema(example = "100")
    @Min(value = 0, message = "Volume must be positive")
    public int volume;
}
