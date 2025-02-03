package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.HandEngine;
import hse.kpo.interfaces.CarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * Factory for hand cars.
 */
@Component
public class HandCarFactory implements CarFactory<EmptyEngineParams> {
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new HandEngine(); // Создаем двигатель без каких-либо параметров

        return new Car(carNumber, engine); // создаем автомобиль с ручным приводом
    }
}
