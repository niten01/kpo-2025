package hse.zoo.infrastructure.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * Feeding schedule data transfer object
 */
@Getter
@RequiredArgsConstructor
public class FeedingScheduleDTO {
    private @NonNull String animalNickname;
    private @NonNull String food;
    private @NonNull Date date;
}
