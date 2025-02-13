package hse.zoo;

import hse.zoo.domains.*;
import hse.zoo.factories.AnimalFactory;
import hse.zoo.factories.ThingFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FactoriesTests {
    @Autowired
    private AnimalFactory animalFactory;

    @Autowired
    private ThingFactory thingFactory;

    @Test
    @DisplayName("Test animal factory create")
    void animalFactoryCreate() {
        var monkey = animalFactory.createAnimal("monkey", 10, 10);
        Assertions.assertInstanceOf(Monkey.class, monkey);
        Assertions.assertEquals(10, monkey.getFood());
        Assertions.assertEquals(10, ((Herbo) monkey).getKindness());

        var tiger = animalFactory.createAnimal("tiger", 10, null);
        Assertions.assertInstanceOf(Tiger.class, tiger);
        Assertions.assertEquals(10, tiger.getFood());

        Assertions.assertDoesNotThrow(() -> animalFactory.createAnimal("rabbit", 10, 1));
        Assertions.assertDoesNotThrow(() -> animalFactory.createAnimal("wolf", 10, 1));

        Assertions.assertThrows(RuntimeException.class, () -> animalFactory.createAnimal("rabbit", 10, 11));
        Assertions.assertThrows(RuntimeException.class, () -> animalFactory.createAnimal("rabbit", 10, -1));
        Assertions.assertThrows(RuntimeException.class, () -> animalFactory.createAnimal("rabbit", 10, null));
        Assertions.assertThrows(RuntimeException.class, () -> animalFactory.createAnimal("non_existent", 10, 1));
    }

    @Test
    @DisplayName("Test thing factory create")
    void thingFactoryCreate() {
        var computer = thingFactory.createThing("computer");
        Assertions.assertTrue(computer.getNumber() >= 0);
        Assertions.assertInstanceOf(Computer.class, computer);

        var table = thingFactory.createThing("table");
        Assertions.assertTrue(table.getNumber() >= 0);
        Assertions.assertInstanceOf(Table.class, table);

        Assertions.assertThrows(RuntimeException.class, () -> thingFactory.createThing("non_existent"));
    }

}
