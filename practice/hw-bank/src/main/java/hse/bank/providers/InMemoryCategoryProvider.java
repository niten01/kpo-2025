package hse.bank.providers;

import hse.bank.domains.Category;
import hse.bank.interfaces.Provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryCategoryProvider implements Provider<Category> {
    private final Map<UUID, Category> categories = new HashMap<>();

    @Override
    public Optional<Category> get(UUID id) {
        return Optional.ofNullable(categories.get(id));
    }

    @Override
    public void add(Category category) {
        categories.put(category.getId(), category);
    }

    @Override
    public void save(Category category) {
        categories.put(category.getId(), category);
    }

    @Override
    public void delete(UUID id) {
        categories.remove(id);
    }
}
