package hse.zoo.domains;

import hse.zoo.params.AnimalParams;
import lombok.ToString;

@ToString(callSuper = true)
public class Wolf extends Predator {
    public Wolf(AnimalParams params) {
        super(params);
    }
}
