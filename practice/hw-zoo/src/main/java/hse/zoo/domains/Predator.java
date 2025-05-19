package hse.zoo.domain;

import hse.zoo.params.AnimalParams;
import lombok.ToString;

@ToString(callSuper = true)
abstract class Predator extends Animal{
    public Predator(AnimalParams params) {
        super(params);
    }
}

