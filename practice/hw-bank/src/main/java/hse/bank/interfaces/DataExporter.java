package hse.bank.interfaces;

import hse.bank.domains.BankAccount;
import hse.bank.domains.Category;
import hse.bank.domains.Operation;

public interface DataExporter {
    void export(BankAccount account);

    void export(Category category);

    void export(Operation operation);
}

