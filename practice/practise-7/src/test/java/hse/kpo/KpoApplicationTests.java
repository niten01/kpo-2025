package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.factories.cars.HandCarFactory;
import hse.kpo.factories.cars.PedalCarFactory;
import hse.kpo.factories.catamarans.HandCatamaranFactory;
import hse.kpo.factories.catamarans.PedalCatamaranFactory;
import hse.kpo.observers.SalesObserver;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.Hse;
import hse.kpo.services.HseCatamaranService;
import hse.kpo.storages.CarStorage;
import hse.kpo.storages.CatamaranStorage;
import hse.kpo.storages.CustomerStorage;
import hse.kpo.services.HseCarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KpoApplicationTests {

    @Autowired
    private CarStorage carStorage;

    @Autowired
    private CatamaranStorage catamaranStorage;

    @Autowired
    private CustomerStorage customerStorage;

    @Autowired
    private HseCarService hseCarService;

    @Autowired
    private HseCatamaranService hseCatamaranService;

    @Autowired
    private PedalCarFactory pedalCarFactory;

    @Autowired
    private HandCarFactory handCarFactory;

    @Autowired
    private PedalCatamaranFactory pedalCatamaranFactory;

    @Autowired
    private HandCatamaranFactory handCatamaranFactory;

    @Autowired
    private SalesObserver salesObserver;

    @Autowired
    private Hse hse;

    @Test
    @DisplayName("Тест загрузки контекста")
    void contextLoads() {
        Assertions.assertNotNull(carStorage);
        Assertions.assertNotNull(customerStorage);
        Assertions.assertNotNull(hseCarService);
    }

    @Test
    @DisplayName("Тест HseCarService")
    void hseCarServiceTest() {
        hse.addCustomer("Ivan1", 6, 4, 150);
        hse.addCustomer("Maksim", 4, 6, 80);
        hse.addCustomer("Petya", 6, 6, 20);
        hse.addCustomer("Nikita", 4, 4, 300);

        hse.addPedalCar(6);
        hse.addPedalCar(6);

        hse.addHandCar();
        hse.addHandCar();

        hse.sell();

        System.out.println(hse.generateReport());
    }

    @Test
    @DisplayName("Тест HseCatamaranService")
    void hseCatamaranServiceTest() {
        hse.addCustomer("Ivan1", 6, 4, 150);
        hse.addCustomer("Maksim", 4, 6, 80);
        hse.addCustomer("Petya", 6, 6, 20);
        hse.addCustomer("Nikita", 4, 4, 300);

        hse.addPedalCatamaran(6);
        hse.addPedalCatamaran(6);

        hse.addHandCatamaran();
        hse.addHandCatamaran();

        hse.sell();

        System.out.println(hse.generateReport());
    }

//	@Test
//	@DisplayName("Тест HseCatamaranService")
//	void hseCatamaranServiceTest() {
//		hseCatamaranService.addObserver(salesObserver);
//		customerStorage.addCustomer(Customer.builder().name("Ivan1").legPower(6).handPower(4).build());
//		customerStorage.addCustomer(Customer.builder().name("Maksim").legPower(4).handPower(6).build());
//		customerStorage.addCustomer(Customer.builder().name("Petya").legPower(6).handPower(6).build());
//		customerStorage.addCustomer(Customer.builder().name("Nikita").legPower(4).handPower(4).build());
//
//		catamaranStorage.addCatamaran(pedalCatamaranFactory, new PedalEngineParams(6));
//		catamaranStorage.addCatamaran(pedalCatamaranFactory, new PedalEngineParams(6));
//
//		catamaranStorage.addCatamaran(handCatamaranFactory, EmptyEngineParams.DEFAULT);
//		catamaranStorage.addCatamaran(handCatamaranFactory, EmptyEngineParams.DEFAULT);
//
//		customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);
//
//		hseCatamaranService.sellCatamarans();
//
//		customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);
//		System.out.println(salesObserver.buildReport());
//	}

}
