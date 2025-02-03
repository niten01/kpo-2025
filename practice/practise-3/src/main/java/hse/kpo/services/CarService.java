package hse.kpo.services;

import hse.kpo.domains.Car;
import hse.kpo.domains.Customer;
import hse.kpo.interfaces.ICarFactory;
import hse.kpo.interfaces.ICarProvider;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Component;

>>>>>>> 835741149bd926d3f9ec06fbe5a6c1b2f58babc7
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
/**
 * Service to provide cars.
 */
=======
>>>>>>> 835741149bd926d3f9ec06fbe5a6c1b2f58babc7
@Component
public class CarService implements ICarProvider {

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
    public <ParamsT> void addCar(ICarFactory<ParamsT> carFactory, ParamsT carParams) {
        // создаем автомобиль из переданной фабрики
        var car = carFactory.createCar(carParams, // передаем параметры
                ++carNumberCounter // передаем номер - номер будет начинаться с 1
        );

        cars.add(car); // добавляем автомобиль
    }
}
