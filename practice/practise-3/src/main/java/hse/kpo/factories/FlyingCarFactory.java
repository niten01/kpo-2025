package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.FlyingEngine;
import hse.kpo.interfaces.ICarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * Factory for flying cars.
 */
@Component
public class FlyingCarFactory implements ICarFactory<EmptyEngineParams> {
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new FlyingEngine();

        return new Car(carNumber, engine);
    }
}
