package studying.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class to represent car owner
 */
@Getter
@ToString
public class Customer {

    private final String name;

    private final int legPower;

    private final int handPower;

    private final int IQ;

    @Setter
    private Car car;

    /**
     * @param name customer name
     * @param legPower power of legs for pedal engines
     * @param handPower power of hands for hand engines
     * @param IQ intelligence for flying cars
     */
    public Customer(String name, int legPower, int handPower, int IQ) {
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.IQ = IQ;
    }
}
