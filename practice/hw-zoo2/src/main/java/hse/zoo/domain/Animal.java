package hse.zoo.domain;

import hse.zoo.domain.enums.Gender;
import hse.zoo.domain.enums.Status;
import hse.zoo.domain.valueobjets.Food;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@ToString
@RequiredArgsConstructor
@Getter
@Slf4j
public class Animal {
    private final String nickname;
    private final String species;
    private final Date birthDate;
    private final Gender gender;
    private final Food favouriteFood;
    private @NonNull Status status;

    private Enclosure enclosure = null;

    public void feed(Food food) {
        log.info("Fed {} with {}", nickname, food);
    }

    public void moveToEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public void removeFromEnclosure() {
        this.enclosure = null;
    }

    public void cure() {
        this.status = Status.HEALTHY;
    }
}
