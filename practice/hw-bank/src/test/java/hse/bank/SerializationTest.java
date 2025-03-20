package hse.bank;

import hse.bank.commands.CreateAccountCommand;
import hse.bank.commands.CreateCategoryCommand;
import hse.bank.commands.CreateOperationCommand;
import hse.bank.domains.BankAccount;
import hse.bank.domains.Operation;
import hse.bank.domains.OperationType;
import hse.bank.facades.AccountFacade;
import hse.bank.facades.CategoryFacade;
import hse.bank.facades.OperationFacade;
import hse.bank.persistence.BankAccountCSVSerializationService;
import hse.bank.persistence.BankAccountYAMLSerializationService;
import hse.bank.persistence.OperationCSVSerializationService;
import hse.bank.persistence.OperationYAMLSerializationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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
    @DisplayName("Test bank account serialization")
    void testAccSerialization() {
        BankAccount account = new BankAccount(1, "acc");

        BankAccountCSVSerializationService acc = new BankAccountCSVSerializationService(accountFacade);

        try {
            acc.serialize(List.of(account), testFilename);
            account = acc.deserialize(testFilename).getFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(1, account.getId());
        Assertions.assertEquals("acc", account.getName());

        BankAccountYAMLSerializationService accYaml = new BankAccountYAMLSerializationService(accountFacade);
        try {
            accYaml.serialize(List.of(account), testFilename);
            account = accYaml.deserialize(testFilename).getFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(1, account.getId());
        Assertions.assertEquals("acc", account.getName());
    }

    @Test
    @DisplayName("Test operation serialization")
    void testOperaionSerialization() {
        accountFacade.addAccount(2, "a");
        categoryFacade.addCategory(3, "a", OperationType.EXPENSE);
        Operation op = new Operation(1, 2, 3, OperationType.EXPENSE, 123.5, new Date(1), "");

        OperationCSVSerializationService opCsv = new OperationCSVSerializationService(operationFacade);

        try {
            opCsv.serialize(List.of(op), testFilename);
            op = opCsv.deserialize(testFilename).getFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Consumer<Operation> testOp = o -> {
            Assertions.assertEquals(1, o.getId());
            Assertions.assertEquals(2, o.getBankAccountId());
            Assertions.assertEquals(3, o.getCategoryId());
            Assertions.assertEquals(OperationType.EXPENSE, o.getType());
            Assertions.assertEquals(123.5, o.getAmount());
            Assertions.assertEquals(new Date(1), o.getDate());
            Assertions.assertEquals("", o.getDescription());
        };

        testOp.accept(op);

        OperationYAMLSerializationService opYaml = new OperationYAMLSerializationService(operationFacade);

        try {
            opYaml.serialize(List.of(op), testFilename);
            op = opYaml.deserialize(testFilename).getFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        testOp.accept(op);
    }
}
