package hse.zoo.domains;

import hse.zoo.interfaces.InventoryInterface;
import lombok.ToString;

@ToString
public abstract class Thing implements InventoryInterface {
    protected int number;

    public Thing(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }
}