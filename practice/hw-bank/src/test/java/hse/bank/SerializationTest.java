package hse.bank;

import hse.bank.commands.CreateAccountCommand;
import hse.bank.commands.CreateCategoryCommand;
import hse.bank.commands.CreateOperationCommand;
import hse.bank.domains.BankAccount;
import hse.bank.facades.AccountFacade;
import hse.bank.facades.CategoryFacade;
import hse.bank.facades.OperationFacade;
import hse.bank.persistence.BankAccountCSVSerializationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class SerializationTest {
    @Autowired
    AccountFacade accountFacade;
    @Autowired
    CategoryFacade categoryFacade;
    @Autowired
    OperationFacade operationFacade;

    private final static String testFilename = ".test";

    @Test
    @DisplayName("Test create command")
    void testAccSerialization() {
        BankAccount account = new BankAccount(1, "acc");

        BankAccountCSVSerializationService acc = new BankAccountCSVSerializationService(" ", accountFacade);

        try {
            acc.serialize(List.of(account), testFilename);
            account = acc.deserialize(testFilename).getFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(1, account.getId());
        Assertions.assertEquals("acc", account.getName());
    }
}
