package hse.bank.persistence;

import hse.bank.domains.Category;
import hse.bank.domains.OperationType;
import hse.bank.facades.CategoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CategoryYAMLSerializationService extends YamlSerializationService<Category> {
    private final CategoryFacade categoryFacade;

    @Override
    protected Category createFromMap(Map<String, Object> data) {
        return categoryFacade.addCategory((Integer) data.get("id"), (String) data.get("name"), OperationType.valueOf((String) data.get("type")));
    }

    @Override
    protected Map<String, Object> createMap(Category item) {
        return Map.of("id", item.getId(), "name", item.getName(), "type", item.getType().toString());
    }
}
