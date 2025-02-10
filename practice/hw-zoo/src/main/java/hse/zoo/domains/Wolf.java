package hse.zoo.domains;

import lombok.ToString;

@ToString
public class Wolf extends Predator {
    public Wolf(int food) {
        super(food);
    }
}
