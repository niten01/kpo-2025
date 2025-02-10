package hse.zoo.domains;

import hse.zoo.interfaces.AliveInterface;
import lombok.ToString;

@ToString
public class Animal implements AliveInterface {
    protected int food;

    public Animal(int food) {
        this.food = food;
    }

    @Override
    public int getFood() {
        return food;
    }
}
