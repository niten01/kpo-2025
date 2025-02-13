package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.CarFactory;

import java.util.ArrayList;
import java.util.List;

import hse.kpo.interfaces.ShipFactory;
import hse.kpo.interfaces.ShipProvider;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * Service to provide ships.
 */
@Component
public class ShipService implements ShipProvider {

    @Getter
    private final List<Ship> ships = new ArrayList<>();

    private int shipNumberCounter = 0;

    /**
     * Tries to provide customer with ship.
     *
     * @param customer customer to provide ship with
     * @return removed ship or null if none compatible
     */
    @Override
    public Ship takeShip(Customer customer) {

        var filteredShips = ships.stream().filter(ship -> ship.isCompatible(customer)).toList();

        var firstShip = filteredShips.stream().findFirst();

        firstShip.ifPresent(ships::remove);

        return firstShip.orElse(null);
    }

    /**
     * Adds new ship.
     *
     * @param shipFactory factory to use for creation
     * @param shipParams  parameters for creation
     * @param <ParamsT>   deduced car parameter type
     */
    public <ParamsT> void addShip(ShipFactory<ParamsT> shipFactory, ParamsT shipParams) {
        // создаем автомобиль из переданной фабрики
        var ship = shipFactory.createShip(shipParams, // передаем параметры
                ++shipNumberCounter // передаем номер - номер будет начинаться с 1
        );

        ships.add(ship); // добавляем автомобиль
    }
}
