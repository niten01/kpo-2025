package hse.zoo.domain;

import hse.zoo.interfaces.AliveInterface;
import hse.zoo.params.AnimalParams;
import lombok.ToString;

@ToString
public class Animal implements AliveInterface {
    private final int food;

    public Animal(AnimalParams params) {
        this.food = params.food();
    }

    @Override
    public int getFood() {
        return food;
    }
}
