package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.FlyingEngine;
import hse.kpo.domains.Ship;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.interfaces.ShipFactory;
import org.springframework.stereotype.Component;

/**
 * Factory for flying ships.
 */
@Component
public class FlyingShipFactory implements ShipFactory<EmptyEngineParams> {
    @Override
    public Ship createShip(EmptyEngineParams shipParams, int shipNumber) {
        var engine = new FlyingEngine();

        return new Ship(shipNumber, engine);
    }
}
