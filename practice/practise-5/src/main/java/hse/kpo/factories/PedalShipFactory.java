package hse.kpo.factories;


import hse.kpo.domains.PedalEngine;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.CarFactory;
import hse.kpo.interfaces.ShipFactory;
import hse.kpo.params.PedalEngineParams;
import org.springframework.stereotype.Component;

/**
 * Factory for pedal ship.
 */
@Component
public class PedalShipFactory implements ShipFactory<PedalEngineParams> {
    @Override
    public Ship createShip(PedalEngineParams shipParams, int shipNumber) {
        var engine = new PedalEngine(shipParams.pedalSize());

        return new Ship(shipNumber, engine);
    }
}
