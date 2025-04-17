package hse.zoo.factories;

import hse.zoo.domain.Animal;
import hse.zoo.domain.Computer;
import hse.zoo.domain.Table;
import hse.zoo.domain.Thing;
import hse.zoo.params.ThingParams;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Creator of things
 */
@Component
public class ThingFactory {

    private static int counter = 0;
    private final Map<String, Class<? extends Thing>> thingTypes = Map.of("table", Table.class, "computer", Computer.class);

    /**
     * Creates new thing instance
     * @param type thing type string
     * @return created thing
     */
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
