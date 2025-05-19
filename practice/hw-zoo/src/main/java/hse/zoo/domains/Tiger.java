package hse.zoo.domain;

import hse.zoo.params.AnimalParams;
import lombok.ToString;

@ToString(callSuper = true)
public class Tiger extends Predator {
    public Tiger(AnimalParams params) {
        super(params);
    }
}

