package hse.kpo.domains;

import hse.kpo.interfaces.Engine;
import lombok.ToString;

/**
 * Domain class for flying engine.
 */
@ToString
public class FlyingEngine implements Engine {
    @Override
    public boolean isCompatible(Customer customer, ProductionTypes type) {
        return customer.getIq() > 300;
    }
}
