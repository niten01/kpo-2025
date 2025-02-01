package hse.kpo.interfaces;

import hse.kpo.domains.Car;

/**
 * Car factory interface.
 *
 * @param <ParamsT> type of engine params
 */
public interface ICarFactory<ParamsT> {
    Car createCar(ParamsT carParams, int carNumber);
}
