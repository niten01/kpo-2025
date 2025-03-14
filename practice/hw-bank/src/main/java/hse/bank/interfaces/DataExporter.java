package hse.bank.interfaces;

import hse.bank.domains.BankAccount;

public interface DataExporter {
    void export(BankAccount account);
    void export(Category category);
    void export(Operation operation);
}

