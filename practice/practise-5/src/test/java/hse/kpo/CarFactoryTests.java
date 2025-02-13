package hse.kpo;

import hse.kpo.factories.FlyingCarFactory;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CarFactoryTests {
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
        Assertions.assertNotNull(pedalCarFactory.createCar(pedalParamsMock, 1));
        Assertions.assertNotNull(pedalCarFactory.createCar(pedalParamsMock, 2));
        Assertions.assertNotNull(pedalCarFactory.createCar(pedalParamsMock, 3));
    }

    @Test
    @DisplayName("Hand car factory : create car")
    void handCarFactoryCreateCarTest() {
        Assertions.assertNotNull(handCarFactory.createCar(EmptyEngineParams.DEFAULT, 1));
        Assertions.assertNotNull(handCarFactory.createCar(EmptyEngineParams.DEFAULT, 2));
        Assertions.assertNotNull(handCarFactory.createCar(EmptyEngineParams.DEFAULT, 3));
    }

    @Test
    @DisplayName("Flying car factory : create car")
    void flyingCarFactoryCreateCarTest() {
        Assertions.assertNotNull(flyingCarFactory.createCar(EmptyEngineParams.DEFAULT, 1));
        Assertions.assertNotNull(flyingCarFactory.createCar(EmptyEngineParams.DEFAULT, 2));
        Assertions.assertNotNull(flyingCarFactory.createCar(EmptyEngineParams.DEFAULT, 3));
    }
}
