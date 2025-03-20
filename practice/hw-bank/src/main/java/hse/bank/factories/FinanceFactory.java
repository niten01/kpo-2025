package hse.bank.factories;

import hse.bank.domains.BankAccount;
import hse.bank.domains.Category;
import hse.bank.domains.Operation;
import hse.bank.domains.OperationType;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Creator of domain objects
 */
public class FinanceFactory {

    public static BankAccount createBankAccount(Integer id, String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Account name cannot be empty.");
        }

        return new BankAccount(id, name);
    }

    public static Category createCategory(Integer id, String name, OperationType type) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty.");
        }
        return new Category(id, name, type);
    }

    public static Operation createOperation(Integer id, BankAccount account, Category category, OperationType type, double amount, String description) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (category.getType() != type) {
            throw new IllegalArgumentException("Operation type does not match category type.");
        }

        return new Operation(id, account.getId(), category.getId(), type, amount, new Date(), description);
    }
}