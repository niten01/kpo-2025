package hse.kpo.factories;

import hse.kpo.domains.HandEngine;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.CarFactory;
import hse.kpo.interfaces.ShipFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * Factory for hand ships.
 */
@Component
public class HandShipFactory implements ShipFactory<EmptyEngineParams> {
    @Override
    public Ship createShip(EmptyEngineParams shipParams, int shipNumber) {
        var engine = new HandEngine(); // Создаем двигатель без каких-либо параметров

        return new Ship(shipNumber, engine); // создаем автомобиль с ручным приводом
    }
}
