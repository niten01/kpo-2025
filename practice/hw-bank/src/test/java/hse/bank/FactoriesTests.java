package hse.bank;

import hse.bank.commands.AnalyticsCommand;
import hse.bank.commands.TimedCommandDecorator;
import hse.bank.domains.AnalyticsResult;
import hse.bank.domains.OperationType;
import hse.bank.factories.CommandFactory;
import hse.bank.factories.FinanceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class FactoriesTests {
    @Autowired
    private CommandFactory commandFactory;

    @Test
    @DisplayName("Test finance factory create")
    void financeFactoryCreate() {
        var acc1 = FinanceFactory.createBankAccount(1, "account");
        var catI = FinanceFactory.createCategory(1, "categoryincome", OperationType.INCOME);
        var catE = FinanceFactory.createCategory(1, "categoryexpense", OperationType.EXPENSE);
        Assertions.assertDoesNotThrow(() -> FinanceFactory.createOperation(1, acc1, catI, OperationType.INCOME, 100, "description"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> FinanceFactory.createOperation(1, acc1, catE, OperationType.INCOME, 100, "description"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> FinanceFactory.createOperation(1, acc1, catI, OperationType.EXPENSE, 100, "description"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> FinanceFactory.createBankAccount(1, ""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> FinanceFactory.createBankAccount(1, null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> FinanceFactory.createCategory(1, "", OperationType.EXPENSE));
        Assertions.assertThrows(IllegalArgumentException.class, () -> FinanceFactory.createCategory(1, null, OperationType.EXPENSE));
    }

    @Test
    @DisplayName("Test command factory create")
    void commandFactoryCreate() {
        var analyticsCommand = commandFactory.createAnalyticsCommand(new Date(), new Date());
        Assertions.assertInstanceOf(AnalyticsCommand.class, analyticsCommand);
        Assertions.assertInstanceOf(AnalyticsResult.class, analyticsCommand.execute());

        Assertions.assertDoesNotThrow(() -> commandFactory.createFixBalanceCommand());

        var acc1 = FinanceFactory.createBankAccount(1, "account");
        Assertions.assertDoesNotThrow(() -> commandFactory.createSetBalanceCommand(acc1.getId(), 100));

        Assertions.assertDoesNotThrow(() -> commandFactory.makeTimed(analyticsCommand));
        Assertions.assertDoesNotThrow(() -> commandFactory.createListCategoriesCommand());
        Assertions.assertDoesNotThrow(() -> commandFactory.createListAccountsCommand());
        Assertions.assertDoesNotThrow(() -> commandFactory.createListOperationsCommand());
    }

}
