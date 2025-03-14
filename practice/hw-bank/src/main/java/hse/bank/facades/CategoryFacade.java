package hse.bank.facades;

import hse.bank.domains.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryFacade {
    private final List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(String name) {
        if (categories.stream().noneMatch(category -> category.getName().equals(name))) {
            throw new IllegalArgumentException("Category not found.");
        }
        categories.removeIf(category -> category.getName().equals(name));
    }

    public List<Category> getCategories() {
        return categories;
    }
}
