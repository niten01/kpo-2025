package hse.bank.persistence;

import hse.bank.domains.BankAccount;
import hse.bank.facades.AccountFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class BankAccountYAMLSerializationService extends YamlSerializationService<BankAccount> {
    private final AccountFacade accountFacade;

    @Override
    protected BankAccount createFromMap(Map<String, Object> data) {
        try {
            return accountFacade.addAccount((Integer) data.get("id"), (String) data.get("name"));
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Invalid arguments for deserialization: " + e.getMessage());
        }
    }

    @Override
    protected Map<String, Object> createMap(BankAccount item) {
        return Map.of("id", item.getId(), "name", item.getName());
    }
}
