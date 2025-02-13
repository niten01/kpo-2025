package hse.zoo;

import hse.zoo.domains.Computer;
import hse.zoo.domains.Monkey;
import hse.zoo.domains.Table;
import hse.zoo.domains.Wolf;
import hse.zoo.interfaces.AliveInterface;
import hse.zoo.services.Zoo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZooTests {
    @Autowired
    private Zoo zoo;


    @Test
    @DisplayName("Test zoo add animals")
    void zooTestAddAnimals() {
        var wolfMock = Mockito.mock(Wolf.class);
        Mockito.when(wolfMock.getFood()).thenReturn(10);
        zoo.addAnimal(wolfMock);
        Assertions.assertInstanceOf(Wolf.class, zoo.getAnimals().getLast());
        var monkeyMock = Mockito.mock(Monkey.class);
        Mockito.when(monkeyMock.getFood()).thenReturn(10);
        zoo.addAnimal(monkeyMock);
        Assertions.assertInstanceOf(Monkey.class, zoo.getAnimals().getLast());

        Assertions.assertEquals(20, zoo.getAnimals().stream().mapToInt(AliveInterface::getFood).sum());
    }

    @Test
    @DisplayName("Test zoo add things")
    void zooTestAddThings() {
        var tableMock = Mockito.mock(Table.class);
        zoo.addThing(tableMock);
        var computerMock = Mockito.mock(Computer.class);
        zoo.addThing(computerMock);
    }


}
