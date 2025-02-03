package hse.kpo.factories;


import hse.kpo.domains.Car;
import hse.kpo.domains.PedalEngine;
import hse.kpo.interfaces.ICarFactory;
import hse.kpo.params.PedalEngineParams;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
/**
 * Factory for pedal cars.
 */
=======
>>>>>>> 835741149bd926d3f9ec06fbe5a6c1b2f58babc7
@Component
public class PedalCarFactory implements ICarFactory<PedalEngineParams> {
    @Override
    public Car createCar(PedalEngineParams carParams, int carNumber) {
        var engine = new PedalEngine(carParams.pedalSize());

        return new Car(carNumber, engine);
    }
}
