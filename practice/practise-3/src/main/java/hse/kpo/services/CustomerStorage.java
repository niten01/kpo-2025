package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.interfaces.ICustomerProvider;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Component;

>>>>>>> 835741149bd926d3f9ec06fbe5a6c1b2f58babc7
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
/**
 * Storage & provider for customers.
 */
=======
>>>>>>> 835741149bd926d3f9ec06fbe5a6c1b2f58babc7
@Component
public class CustomerStorage implements ICustomerProvider {
    private final List<Customer> customers = new ArrayList<>();

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer); // просто добавляем покупателя в список
    }
}
