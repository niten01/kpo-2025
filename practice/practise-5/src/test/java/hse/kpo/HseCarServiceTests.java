package hse.kpo;

import hse.kpo.services.HseCarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HseCarServiceTests {
    @Autowired
    private HseCarService hseCarService;


    @Test
    @DisplayName("HSE car service : sell cars")
    void hseCarServiceSellCarsTest() {
        Assertions.assertDoesNotThrow(() -> {
            hseCarService.sellCars();
        });
    }

}
