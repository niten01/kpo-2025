package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.services.CustomerStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerProviderTests {
    @Autowired
    private CustomerStorage customerStorage;

    @Test
    @DisplayName("Customer storage : add customer")
    void customerStorageAddCustomerTest() {
        customerStorage.addCustomer(new Customer("Ivan1", 6, 4, 100));
        Assertions.assertEquals(1, customerStorage.getCustomers().size());
        customerStorage.addCustomer(new Customer("", 4, 6, -1));
        Assertions.assertEquals(2, customerStorage.getCustomers().size());
        customerStorage.addCustomer(new Customer("Petya", 6, 6, 52));
        Assertions.assertEquals(3, customerStorage.getCustomers().size());
        customerStorage.addCustomer(new Customer("Nikita", 4, 4, 1000));
        Assertions.assertEquals(4, customerStorage.getCustomers().size());
    }

}
