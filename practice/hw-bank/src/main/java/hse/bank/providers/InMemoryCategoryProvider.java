package hse.bank.providers;

import hse.bank.domains.Category;
import hse.bank.interfaces.Provider;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * In-memory storage of categories
 */
@Component
public class InMemoryCategoryProvider implements Provider<Category> {
    private final Map<Integer, Category> categories = new HashMap<>();

    @Override
    public List<Category> getAll() {
        return categories.values().stream().toList();
    }

    @Override
    public Optional<Category> get(int id) {
        return Optional.ofNullable(categories.get(id));
    }

    @Override
    public Category add(Category category) {
        categories.put(category.getId(), category);
        return category;
    }

    @Override
    public void save(Category category) {
        if (get(category.getId()).isEmpty()) {
            throw new IllegalArgumentException("Category not found.");
        }
        categories.put(category.getId(), category);
    }

    @Override
    public void delete(int id) {
        categories.remove(id);
    }
}
