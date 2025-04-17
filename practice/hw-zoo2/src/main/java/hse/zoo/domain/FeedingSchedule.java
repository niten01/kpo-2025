package hse.zoo.domain;

import hse.zoo.domain.valueobjets.Food;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class FeedingSchedule {
    private final Animal animal;
    private final Food food;
    private @NonNull Date date;
    @Setter
    private boolean done = false;

    void changeSchedule(Date date) {
        this.date = date;
    }
}
