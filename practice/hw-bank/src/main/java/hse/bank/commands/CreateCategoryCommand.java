package hse.bank.commands;

import hse.bank.domains.Category;
import hse.bank.facades.AnalyticsFacade;
import hse.bank.facades.CategoryFacade;
import hse.bank.interfaces.Command;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCategoryCommand implements Command<Void> {
    private CategoryFacade categoryFacade;
    private Category category;

    @Override
    public Void execute() {
        categoryFacade.addCategory(category);
        return null;
    }
}

