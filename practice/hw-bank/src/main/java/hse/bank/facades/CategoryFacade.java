package hse.bank.facades;

import hse.bank.domains.BankAccount;
import hse.bank.domains.Category;
import hse.bank.domains.OperationType;
import hse.bank.interfaces.Provider;
import hse.bank.factories.FinanceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Category management service
 */
@Component
@RequiredArgsConstructor
public class CategoryFacade {
    private final Provider<Category> categoriesProvider;
    private static int defaultIDCounter = 0;

    public Category getCategory(int id) {
        var category = categoriesProvider.get(id);
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Category not found.");
        }
        return category.get();
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categoriesProvider.getAll());
    }

    public Category addCategory(Integer id, String name, OperationType type) {
        return categoriesProvider.add(FinanceFactory.createCategory(id, name, type));
    }

    public Category addCategory(String name, OperationType type) {
        return addCategory(defaultIDCounter++, name, type);
    }

    public void deleteCategory(Category category) {
        categoriesProvider.delete(category.getId());
    }

    public void saveCategory(Category category) {
        categoriesProvider.save(category);
    }

}
