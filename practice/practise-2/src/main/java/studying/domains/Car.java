package studying.domains;

import lombok.Getter;
import lombok.ToString;
import studying.interfaces.IEngine;

/**
 * Car domain class
 */
@ToString
public class Car {

    private IEngine engine;

    @Getter
    private int VIN;

    /**
     * @param VIN car identifier
     * @param engine engine to install
     */
    public Car(int VIN, IEngine engine) {
        this.VIN = VIN;
        this.engine = engine;
    }

    /**
     * @param customer customer to check compatibility with
     * @return whether this car can be owned by customer
     */
    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer); // внутри метода просто вызываем соответствующий метод двигателя
    }
}
