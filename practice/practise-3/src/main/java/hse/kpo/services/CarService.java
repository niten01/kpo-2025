package hse.kpo.services;

import hse.kpo.domains.Car;
import hse.kpo.domains.Customer;
import hse.kpo.interfaces.CarFactory;
import hse.kpo.interfaces.CarProvider;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Service to provide cars.
 */
@Component
public class CarService implements CarProvider {

    private final List<Car> cars = new ArrayList<>();

    private int carNumberCounter = 0;

    /**
     * Tries to provide customer with car.
     *
     * @param customer customer to provide car with
     * @return removed car or null if none compatible
     */
    @Override
    public Car takeCar(Customer customer) {

        var filteredCars = cars.stream().filter(car -> car.isCompatible(customer)).toList();

        var firstCar = filteredCars.stream().findFirst();

        firstCar.ifPresent(cars::remove);

        return firstCar.orElse(null);
    }

    /**
     * Adds new car.
     *
     * @param carFactory factory to use for creation
     * @param carParams  parameters for creation
     * @param <ParamsT>  deduced car parameter type
     */
    public <ParamsT> void addCar(CarFactory<ParamsT> carFactory, ParamsT carParams) {
        // создаем автомобиль из переданной фабрики
        var car = carFactory.createCar(carParams, // передаем параметры
                ++carNumberCounter // передаем номер - номер будет начинаться с 1
        );

        cars.add(car); // добавляем автомобиль
    }
}
