package hse.bank.persistence;

import hse.bank.domains.Category;
import hse.bank.domains.OperationType;
import hse.bank.facades.CategoryFacade;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class CategoryYAMLSerializationService extends YamlSerializationService<Category> {
    private final CategoryFacade categoryFacade;

    @Override
    protected Category createFromMap(Map<String, Object> data) {
        return categoryFacade.addCategory((Integer) data.get("id"), (String) data.get("name"), OperationType.valueOf((String) data.get("type")));
    }
}
