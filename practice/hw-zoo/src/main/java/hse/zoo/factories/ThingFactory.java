package hse.zoo.factories;

import hse.zoo.domains.Computer;
import hse.zoo.domains.Table;
import hse.zoo.domains.Thing;

public class ThingFactory {

    private static int counter = 0;

    public static Thing createThing(String type) {
        counter++;
        return switch (type.toLowerCase()) {
            case "table" -> new Table(counter);
            case "computer" -> new Computer(counter);
            default -> throw new IllegalArgumentException("Invalid thing type.");
        };
    }
}
