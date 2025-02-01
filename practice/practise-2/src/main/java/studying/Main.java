package studying;

import studying.domains.Customer;
import studying.factories.FlyingCarFactory;
import studying.factories.HandCarFactory;
import studying.factories.PedalCarFactory;
import studying.params.EmptyEngineParams;
import studying.params.PedalEngineParams;
import studying.services.CarService;
import studying.services.CustomerStorage;
import studying.services.HseCarService;

public class Main {
    public static void main(String[] args) {
        var carService = new CarService();
        var customerStorage = new CustomerStorage();
        var hseCarService = new HseCarService(carService,customerStorage);
        var pedalCarFactory = new PedalCarFactory();
        var flyingCarFactory = new FlyingCarFactory();
        var handCarFactory = new HandCarFactory();

        customerStorage.addCustomer(new Customer("a", 6, 4, 200));
        customerStorage.addCustomer(new Customer("b", 4, 6, 1));
        customerStorage.addCustomer(new Customer("c", 6, 6, 52));
        customerStorage.addCustomer(new Customer("d", 4, 4, 1000));

        carService.addCar(pedalCarFactory, new PedalEngineParams(4));
        carService.addCar(pedalCarFactory, new PedalEngineParams(7));
        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);
        carService.addCar(flyingCarFactory, EmptyEngineParams.DEFAULT);
        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        System.out.println(customerStorage);

        hseCarService.sellCars();

        for(var customer : customerStorage.getCustomers()) {
            System.out.println(customer.toString());
        }
    }
}
