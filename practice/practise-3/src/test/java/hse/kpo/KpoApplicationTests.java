package hse.kpo;

import hse.kpo.domains.*;
import hse.kpo.factories.FlyingCarFactory;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class KpoApplicationTests {

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerStorage customerStorage;

    @Autowired
    private HseCarService hseCarService;

    @Autowired
    private PedalCarFactory pedalCarFactory;

    @Autowired
    private HandCarFactory handCarFactory;

    @Autowired
    private FlyingCarFactory flyingCarFactory;

    @Test
    @DisplayName("Pedal car factory : create car")
    void pedalCarFactoryCreateCarTest() {
        var pedalParamsMock = Mockito.mock(PedalEngineParams.class);
        Mockito.when(pedalParamsMock.pedalSize()).thenReturn(1);
        pedalCarFactory.createCar(pedalParamsMock, 1);
        pedalCarFactory.createCar(pedalParamsMock, 2);
        pedalCarFactory.createCar(pedalParamsMock, 3);
    }

    @Test
    @DisplayName("Hand car factory : create car")
    void handCarFactoryCreateCarTest() {
        handCarFactory.createCar(EmptyEngineParams.DEFAULT, 1);
        handCarFactory.createCar(EmptyEngineParams.DEFAULT, 2);
        handCarFactory.createCar(EmptyEngineParams.DEFAULT, 3);
    }

    @Test
    @DisplayName("Flying car factory : create car")
    void flyingCarFactoryCreateCarTest() {
        flyingCarFactory.createCar(EmptyEngineParams.DEFAULT, 1);
        flyingCarFactory.createCar(EmptyEngineParams.DEFAULT, 2);
        flyingCarFactory.createCar(EmptyEngineParams.DEFAULT, 3);
    }

    @Test
    @DisplayName("Customer storage : add customer")
    void customerStorageAddCustomerTest() {
        customerStorage.addCustomer(new Customer("Ivan1", 6, 4, 100));
        customerStorage.addCustomer(new Customer("", 4, 6, -1));
        customerStorage.addCustomer(new Customer("Petya", 6, 6, 52));
        customerStorage.addCustomer(new Customer("Nikita", 4, 4, 1000));
    }

    @Test
    @DisplayName("Car service : add car")
    void carServiceAddCarTest() {
        var handCarFactoryMock = Mockito.mock(HandCarFactory.class);
        Mockito.when(handCarFactoryMock.createCar(Mockito.any(EmptyEngineParams.class), Mockito.anyInt())).thenReturn(new Car(1, new HandEngine()));
        var pedalCarFactoryMock = Mockito.mock(PedalCarFactory.class);
        Mockito.when(pedalCarFactoryMock.createCar(Mockito.any(PedalEngineParams.class), Mockito.anyInt())).thenReturn(new Car(1, new PedalEngine(1)));
        var flyingCarFactoryMock = Mockito.spy(FlyingCarFactory.class);
        Mockito.when(flyingCarFactoryMock.createCar(Mockito.any(EmptyEngineParams.class), Mockito.anyInt())).thenReturn(new Car(1, new FlyingEngine()));
        carService.addCar(handCarFactoryMock, EmptyEngineParams.DEFAULT);
        carService.addCar(pedalCarFactoryMock, new PedalEngineParams(6));
        carService.addCar(flyingCarFactoryMock, EmptyEngineParams.DEFAULT);
    }

    @Test
    @DisplayName("Car service : take car when empty")
    void carServiceTakeCarFromEmptyTest() {
        var car = carService.takeCar(new Customer("test", 0, 0, 0));
        Assert.isNull(car, "car should be null");
    }

    @Test
    @DisplayName("HSE car service : sell cars")
    void hseCarServiceSellCarsTest() {
        hseCarService.sellCars();
    }

}
