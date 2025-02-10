package hse.zoo.domains;

import lombok.ToString;

@ToString
public class Monkey extends Herbo {
    public Monkey(int food, int kindness) {
        super(food, kindness);
    }
}