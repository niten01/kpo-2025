package hse.kpo.domains;

import hse.kpo.interfaces.Engine;
import lombok.ToString;

/**
 * Domain class for hand engine.
 */
@ToString
public class HandEngine implements Engine {
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getHandPower() > 5;
    }
}
