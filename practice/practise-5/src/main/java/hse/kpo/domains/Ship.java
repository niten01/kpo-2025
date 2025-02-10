package hse.kpo.domains;

import hse.kpo.interfaces.Engine;
import lombok.Getter;
import lombok.ToString;

/**
 * Domain class for ship.
 */
@ToString
public class Ship {

    private final Engine engine;

    @Getter
    private final int vin;

    public Ship(int vin, Engine engine) {
        this.vin = vin;
        this.engine = engine;
    }

    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer); // внутри метода просто вызываем соответствующий метод двигателя
    }
}
