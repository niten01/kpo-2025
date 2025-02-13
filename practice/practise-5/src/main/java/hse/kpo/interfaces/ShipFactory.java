package hse.kpo.interfaces;

import hse.kpo.domains.Ship;

/**
 * Ship factory interface.
 *
 * @param <ParamsT> type of engine params
 */
public interface ShipFactory<ParamsT> {
    Ship createShip(ParamsT carParams, int carNumber);
}
