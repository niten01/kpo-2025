package studying;

public class Main {
    public static void main(String[] args) {
        var carService = new CarService();
        var customerStorage = new CustomerStorage();
        var hseCarService = new HseCarService(carService,customerStorage);
        var pedalCarFactory = new PedalCarFactory();
        var handCarFactory = new HandCarFactory();

        customerStorage.addCustomer(new Customer("a", 6, 4));
        customerStorage.addCustomer(new Customer("b", 4, 6));
        customerStorage.addCustomer(new Customer("c", 6, 6));
        customerStorage.addCustomer(new Customer("d", 4, 4));

        carService.addCar(pedalCarFactory, new PedalEngineParams(4));
        carService.addCar(pedalCarFactory, new PedalEngineParams(7));
        carService.addCar(handCarFactory, new EmptyEngineParams());
        carService.addCar(handCarFactory, new EmptyEngineParams());

        System.out.println(customerStorage.toString());

        hseCarService.sellCars();

        for(var customer : customerStorage.getCustomers()) {
            System.out.println(customer.toString());
        }
    }
}
