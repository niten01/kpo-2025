package hse.zoo.domain;

import hse.zoo.params.ThingParams;
import lombok.ToString;

@ToString(callSuper = true)
public class Table extends Thing {
    public Table(ThingParams params) {
        super(params);
    }
}