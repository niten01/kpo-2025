package hse.kpo.factories.cars;


import hse.kpo.domains.Car;
import hse.kpo.domains.PedalEngine;
import hse.kpo.domains.WheelCatamaran;
import hse.kpo.factories.catamarans.HandCatamaranFactory;
import hse.kpo.interfaces.cars.CarFactory;
import hse.kpo.params.EmptyEngineParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Фабрика для создания машин с {@link PedalEngine} типом двигателя.
 */
@Component
@RequiredArgsConstructor
public class WheelCatamaranFactory implements CarFactory<EmptyEngineParams> {
    HandCatamaranFactory handCatamaranFactory;

    @Override
    public Car create(EmptyEngineParams carParams, int carNumber) {
        return new WheelCatamaran(handCatamaranFactory.create(carParams, carNumber));
    }
}