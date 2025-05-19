package hse.zoo.infrastructure.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

/**
 * Shows what data is needed to create a feeding schedule (and it's constraints)
 */
public class CreateFeedingScheduleRequest {
    @NotBlank(message = "Animal nickname cannot be empty")
    @Schema(example = "Bobik")
    public String animalNickname;

    @NotBlank(message = "Food cannot be empty")
    @Schema(example = "Meat")
    public String food;

    @NotNull(message = "Date cannot be empty")
    @Schema(example = "2022-12-12")
    public Date date;
}
