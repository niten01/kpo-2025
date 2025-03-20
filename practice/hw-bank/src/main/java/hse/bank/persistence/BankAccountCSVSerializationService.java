package hse.bank.persistence;

import hse.bank.domains.BankAccount;
import hse.bank.facades.AccountFacade;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankAccountCSVSerializationService extends CsvSerializerService<BankAccount> {
    private final AccountFacade accountFacade;

    @Override
    protected String serializeItem(BankAccount account) {
        return account.getId() + delimiter + account.getName();
    }

    @Override
    protected BankAccount deserializeItem(String data) {
        String[] parts = data.split(delimiter);
        try {
            return accountFacade.addAccount(Integer.parseInt(parts[0]), parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid arguments for deserialization: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid number of arguments for deserialization");
        }
    }
}
