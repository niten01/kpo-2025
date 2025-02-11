package hse.zoo.domains;

import hse.zoo.interfaces.InventoryInterface;
import hse.zoo.params.ThingParams;
import lombok.ToString;

@ToString
public abstract class Thing implements InventoryInterface {
    protected int number;

    public Thing(ThingParams params) {
        this.number = params.number();
    }

    @Override
    public int getNumber() {
        return number;
    }
}