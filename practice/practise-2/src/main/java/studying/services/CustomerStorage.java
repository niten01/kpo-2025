package studying.services;

import lombok.ToString;
import studying.domains.Customer;
import studying.interfaces.ICustomerProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Storage & provider for customers
 */
@ToString
public class CustomerStorage implements ICustomerProvider {
    private final List<Customer> customers = new ArrayList<>();

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * @param customer customer to add to storage
     */
    public void addCustomer(Customer customer)
    {
        customers.add(customer);
    }
}
