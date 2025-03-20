package hse.bank.persistence;

import hse.bank.domains.Category;
import hse.bank.domains.OperationType;
import hse.bank.facades.CategoryFacade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryCSVSerializationService extends AbstractSerializationService<Category> {
    private final String delimiter;
    private final CategoryFacade categoryFacade;

    @Override
    protected String serializeItem(Category category) {
        return category.getId() + delimiter + category.getName() + delimiter + category.getType().toString();
    }

    @Override
    protected Category deserializeItem(String data) {
        String[] parts = data.split(delimiter);
        try {
            return categoryFacade.addCategory(Integer.parseInt(parts[0]), parts[1], OperationType.valueOf(parts[2]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid arguments for deserialization: " + e.getMessage());
        }
    }
}
