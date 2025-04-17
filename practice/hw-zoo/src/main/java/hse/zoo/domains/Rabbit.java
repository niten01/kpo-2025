package hse.zoo.domain;

import hse.zoo.params.HerboParams;
import lombok.ToString;

@ToString(callSuper = true)
public class Rabbit extends Herbo {
    public Rabbit(HerboParams params) {
        super(params);
    }
}
