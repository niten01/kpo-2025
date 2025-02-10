package hse.zoo.domains;

import lombok.ToString;

@ToString
public class Rabbit extends Herbo {
    public Rabbit(int food, int kindness) {
        super(food, kindness);
    }
}
