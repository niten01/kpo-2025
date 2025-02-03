package hse.kpo.domains;

import hse.kpo.interfaces.IEngine;
import lombok.Getter;
import lombok.ToString;

/**
 * Domain class for car.
 */
@ToString
public class Car {

    private final IEngine engine;

    @Getter
    private final int vin;

    public Car(int vin, IEngine engine) {
        this.vin = vin;
        this.engine = engine;
    }

    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer); // внутри метода просто вызываем соответствующий метод двигателя
    }
}
