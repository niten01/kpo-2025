package hse.kpo.services;

import hse.kpo.interfaces.CustomerProvider;

import java.util.Objects;

import hse.kpo.interfaces.ShipProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Service for distributing cars among customers.
 */
@Component
@RequiredArgsConstructor
public class HseShipService {

    private final ShipProvider shipProvider;

    private final CustomerProvider customerProvider;

    /**
     * Tries to distribute ships among customers.
     */
    public void sellShip() {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getCar())).forEach(customer -> {
            var ship = shipProvider.takeShip(customer);
            if (Objects.nonNull(ship)) {
                customer.setShip(ship);
            }
        });
    }
}
