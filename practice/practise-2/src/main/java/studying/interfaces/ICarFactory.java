package studying.interfaces;

import studying.domains.Car;

/**
 * Car factory interface
 * @param <TParams> car engine parameters
 */
public interface ICarFactory<TParams> {
    /**
     * @param carParams parameters to create car with
     * @param carNumber car identifier
     * @return new car
     */
    Car createCar(TParams carParams, int carNumber);
}
