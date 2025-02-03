package hse.kpo.domains;

import hse.kpo.interfaces.Engine;
import lombok.Getter;
import lombok.ToString;

/**
 * Domain class for pedal engine.
 */
@ToString
@Getter
public class PedalEngine implements Engine {
    private final int size;

    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getLegPower() > 5;
    }

    public PedalEngine(int size) {
        this.size = size;
    }
}
