package studying.services;

import lombok.ToString;
import studying.domains.Car;
import studying.domains.Customer;
import studying.interfaces.ICarFactory;
import studying.interfaces.ICarProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Car provider implementation
 */
@ToString
public class CarService implements ICarProvider {

    private final List<Car> cars = new ArrayList<>();

    private int carNumberCounter = 0;

    /**
     * @param customer customer to provide car with
     * @return car for customer
     */
    @Override
    public Car takeCar(Customer customer) {

        var filteredCars = cars.stream().filter(car -> car.isCompatible(customer)).toList();

        if(filteredCars.isEmpty())
            return null;

        var firstCar = filteredCars.stream().findFirst().get();
        cars.remove(firstCar);
        return firstCar;
    }

    /**
     * @param carFactory factory to create new car
     * @param carParams parameters for car creation
     * @param <TParams> deduced car parameter type
     */
    public <TParams> void addCar(ICarFactory<TParams> carFactory, TParams carParams)
    {
        // создаем автомобиль из переданной фабрики
        var car = carFactory.createCar(
                carParams, // передаем параметры
                ++carNumberCounter // передаем номер - номер будет начинаться с 1
        );

        cars.add(car); // добавляем автомобиль
    }
}
