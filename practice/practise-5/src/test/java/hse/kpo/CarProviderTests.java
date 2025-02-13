package hse.kpo;

import hse.kpo.domains.*;
import hse.kpo.factories.FlyingCarFactory;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class CarProviderTests {


    @Autowired
    private CarService carService;

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
        Assertions.assertEquals(1, carService.getCars().size());
        carService.addCar(pedalCarFactoryMock, new PedalEngineParams(6));
        Assertions.assertEquals(2, carService.getCars().size());
        carService.addCar(flyingCarFactoryMock, EmptyEngineParams.DEFAULT);
        Assertions.assertEquals(3, carService.getCars().size());
    }

    @Test
    @DisplayName("Car service : take car when empty")
    void carServiceTakeCarFromEmptyTest() {
        var car = carService.takeCar(new Customer("test", 0, 0, 0));
        Assert.isNull(car, "car should be null");
    }

}
