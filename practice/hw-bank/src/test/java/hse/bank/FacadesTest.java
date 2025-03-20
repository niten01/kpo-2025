package hse.bank;

import hse.bank.domains.OperationType;
import hse.bank.facades.AccountFacade;
import hse.bank.facades.CategoryFacade;
import hse.bank.facades.OperationFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Date;

@SpringBootTest
class FacadesTest {
    @Autowired
    AccountFacade accountFacade;
    @Autowired
    CategoryFacade categoryFacade;
    @Autowired
    OperationFacade operationFacade;

    @Test
    @DisplayName("Account facade test")
    void accountFacadeTest() {
        for (var acc : accountFacade.getAllAccounts()) {
            accountFacade.deleteAccount(acc);
        }

        Assertions.assertDoesNotThrow(() -> accountFacade.addAccount("a"));
        Assertions.assertEquals(1, accountFacade.getAllAccounts().size());
        var accId = accountFacade.getAllAccounts().getFirst().getId();
        var acc = accountFacade.getAccount(accId);
        acc.setBalance(1000);
        accountFacade.saveAccount(acc);

        categoryFacade.addCategory("a", OperationType.INCOME);
        var cat = categoryFacade.getAllCategories().getFirst();
        operationFacade.addOperation(acc.getId(), cat.getId(), OperationType.INCOME, 1, new Date(), "");

        accountFacade.fixBalances();
        Assertions.assertEquals(1, accountFacade.getAccount(accId).getBalance());
    }


}
