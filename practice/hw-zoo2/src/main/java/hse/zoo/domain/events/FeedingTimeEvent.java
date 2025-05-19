package hse.zoo.domain.events;


import hse.zoo.domain.valueobjets.Food;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@ToString
public class FeedingTimeEvent {
    private final String animalNickname;
    private final Date date;
    private final Food food;
}
