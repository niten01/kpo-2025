package hse.kpo.domains;

import hse.kpo.interfaces.Engine;
import lombok.Getter;
import lombok.ToString;

/**
 * Domain class for car.
 */
@ToString
public class Car {

    private final Engine engine;

    @Getter
    private final int vin;

    public Car(int vin, Engine engine) {
        this.vin = vin;
        this.engine = engine;
    }

    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer,ProductionTypes.CAR ); // внутри метода просто вызываем соответствующий метод двигателя
    }
}
