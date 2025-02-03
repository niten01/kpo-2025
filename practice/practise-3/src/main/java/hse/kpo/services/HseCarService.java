package hse.kpo.services;

import hse.kpo.interfaces.ICarProvider;
import hse.kpo.interfaces.ICustomerProvider;
<<<<<<< HEAD
=======
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

>>>>>>> 835741149bd926d3f9ec06fbe5a6c1b2f58babc7
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
/**
 * Service for distributing cars among customers.
 */
=======
>>>>>>> 835741149bd926d3f9ec06fbe5a6c1b2f58babc7
@Component
@RequiredArgsConstructor
public class HseCarService {

    private final ICarProvider carProvider;

    private final ICustomerProvider customerProvider;

<<<<<<< HEAD
    /**
     * Tries to distribute cars among customers.
     */
    public void sellCars() {
=======
    public void sellCars()
    {
>>>>>>> 835741149bd926d3f9ec06fbe5a6c1b2f58babc7
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