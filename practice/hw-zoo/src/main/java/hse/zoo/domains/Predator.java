package hse.zoo.domains;

import lombok.ToString;

@ToString
abstract class Predator extends Animal{
    public Predator(int food) {
        super(food);
    }
}

