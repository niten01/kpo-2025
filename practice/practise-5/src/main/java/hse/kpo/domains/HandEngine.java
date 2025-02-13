package hse.kpo.domains;

import hse.kpo.interfaces.Engine;
import lombok.ToString;

/**
 * Domain class for hand engine.
 */
@ToString
public class HandEngine implements Engine {
    @Override
    public boolean isCompatible(Customer customer, ProductionTypes type) {
        return switch (type) {
            case ProductionTypes.CAR -> customer.getHandPower() > 5;
            case ProductionTypes.CATAMARAN -> customer.getHandPower() > 2;
        };
    }
}
