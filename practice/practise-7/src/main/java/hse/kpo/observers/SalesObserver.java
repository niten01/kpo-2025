package hse.kpo.observers;

import hse.kpo.domains.Customer;
import hse.kpo.domains.Report;
import hse.kpo.enums.ProductionTypes;

public interface SalesObserver {
    void onSale(Customer customer, ProductionTypes productType, int vin);
    void addThing(ProductionTypes type);

    void checkCustomers();

    Report buildReport();
}
