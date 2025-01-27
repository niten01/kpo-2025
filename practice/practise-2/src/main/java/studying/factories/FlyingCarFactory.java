package studying.factories;

import studying.domains.Car;
import studying.domains.FlyingEngine;
import studying.interfaces.ICarFactory;
import studying.params.EmptyEngineParams;

public class FlyingCarFactory implements ICarFactory<EmptyEngineParams> {
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new FlyingEngine();

        return new Car(carNumber, engine);
    }
}
