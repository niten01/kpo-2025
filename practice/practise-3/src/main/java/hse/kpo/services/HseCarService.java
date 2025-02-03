package hse.kpo.services;

import hse.kpo.interfaces.CarProvider;
import hse.kpo.interfaces.CustomerProvider;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Service for distributing cars among customers.
 */
@Component
@RequiredArgsConstructor
public class HseCarService {

    private final CarProvider carProvider;

    private final CustomerProvider customerProvider;

    /**
     * Tries to distribute cars among customers.
     */
    public void sellCars() {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getCar())).forEach(customer -> {
            var car = carProvider.takeCar(customer);
            if (Objects.nonNull(car)) {
                customer.setCar(car);
            }
        });
    }
}
