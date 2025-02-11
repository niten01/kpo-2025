package hse.zoo.factories;

import hse.zoo.domains.Animal;
import hse.zoo.domains.Computer;
import hse.zoo.domains.Table;
import hse.zoo.domains.Thing;
import hse.zoo.params.ThingParams;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Component
public class ThingFactory {

    private static int counter = 0;
    private final Map<String, Class<? extends Thing>> thingTypes = Map.of("table", Table.class, "computer", Computer.class);

    public Thing createThing(String type) {
        counter++;
        Class<? extends Thing> thingClass = thingTypes.get(type.toLowerCase());
        if (thingClass == null) {
            throw new IllegalArgumentException("Invalid thing type.");
        }

        try {
            return thingClass.getConstructor(ThingParams.class).newInstance(new ThingParams(counter));
        } catch (Exception e) {
            throw new RuntimeException("Failed to create thing instance", e);
        }
    }
}
