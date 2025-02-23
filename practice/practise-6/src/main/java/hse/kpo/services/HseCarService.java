package hse.kpo.services;

import hse.kpo.annotations.Sales;
import hse.kpo.interfaces.cars.CarProvider;
import hse.kpo.interfaces.CustomerProvider;
import hse.kpo.domains.Customer;
import hse.kpo.enums.ProductionTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import hse.kpo.interfaces.SalesObserver;

/**
 * Сервис продажи машин.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class HseCarService {

    final List<SalesObserver> observers = new ArrayList<>();

    private final CarProvider carProvider;

    private final CustomerProvider customerProvider;

    public void addObserver(SalesObserver observer) {
        observers.add(observer);
    }

    private void notifyObserversForSale(Customer customer, ProductionTypes productType, int vin) {
        observers.forEach(obs -> obs.onSale(customer, productType, vin));
    }

    /**
     * Метод продажи машин
     */
    @Sales
    public void sellCars() {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getCar()))
                .forEach(customer -> {
                    var car = carProvider.takeCar(customer);
                    if (Objects.nonNull(car)) {
                        notifyObserversForSale(customer, ProductionTypes.CAR, car.getVin());
                        customer.setCar(car);
                    } else {
                        log.warn("No car in CarService");
                    }
                });
    }
}