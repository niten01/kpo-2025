package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.factories.FlyingShipFactory;
import hse.kpo.factories.HandShipFactory;
import hse.kpo.factories.PedalShipFactory;
import hse.kpo.interfaces.ShipProvider;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.reports.ReportBuilder;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;
import hse.kpo.services.HseShipService;
import hse.kpo.services.ShipService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point class.
 */
@SpringBootApplication
public class KpoApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(KpoApplication.class, args);
        var hseShipService = context.getBean(HseShipService.class);

        var handShipFactory = context.getBean(HandShipFactory.class);
        var flyingShipFactory = context.getBean(FlyingShipFactory.class);
        var pedalShipFactory = context.getBean(PedalShipFactory.class);
        var shipService = context.getBean(ShipService.class);
        shipService.addShip(handShipFactory, EmptyEngineParams.DEFAULT);
        shipService.addShip(flyingShipFactory, EmptyEngineParams.DEFAULT);
        shipService.addShip(pedalShipFactory, new PedalEngineParams(3));

        var customerStorage = context.getBean(CustomerStorage.class);
        customerStorage.addCustomer(new Customer("Ivan1", 6, 4, 100));
        customerStorage.addCustomer(new Customer("", 4, 6, -1));
        customerStorage.addCustomer(new Customer("Petya", 6, 6, 52));
        customerStorage.addCustomer(new Customer("Nikita", 4, 4, 1000));

        var reportBuilder = new ReportBuilder().addOperation("Инициализация системы").addCustomers(customerStorage.getCustomers()).addShips(shipService.getShips());

        hseShipService.sellShip();

        var report = reportBuilder.addOperation("Продажа автомобилей").addCustomers(customerStorage.getCustomers()).addShips(shipService.getShips()).build();

        System.out.println(report.toString());
    }
}
