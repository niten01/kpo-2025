package hse.bank.commands;

import hse.bank.domains.Category;
import hse.bank.facades.CategoryFacade;
import hse.bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListCategoriesCommand implements Command<List<Category>> {
    private final CategoryFacade categories;

    @Override
    public List<Category> execute() {
        return categories.getAllCategories();
    }
}
