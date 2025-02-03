package hse.kpo.domains;

import hse.kpo.interfaces.IEngine;
import lombok.ToString;

/**
 * Domain class for flying engine.
 */
@ToString
public class FlyingEngine implements IEngine {
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getIq() > 300;
    }
}
