package hse.bank;

import hse.bank.domains.BankAccount;
import hse.bank.domains.Category;
import hse.bank.domains.Operation;
import hse.bank.domains.OperationType;
import hse.bank.providers.InMemoryAccountProvider;
import hse.bank.providers.InMemoryCategoryProvider;
import hse.bank.providers.InMemoryOperationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class InMemoryProviderTest {
    @Autowired
    InMemoryAccountProvider accountProvider;
    @Autowired
    InMemoryCategoryProvider categoryProvider;
    @Autowired
    InMemoryOperationProvider operationProvider;


    @Test
    @DisplayName("Test account provider add consistency")
    void testInMemoryAccountConsistency() {
        var accMock = Mockito.mock(BankAccount.class);
        Mockito.when(accMock.getId()).thenReturn(0);
        Mockito.when(accMock.getName()).thenReturn("a");
        Mockito.when(accMock.getBalance()).thenReturn(0.0);
        accountProvider.add(accMock);
        Assertions.assertTrue(accountProvider.get(0).isPresent());
        Assertions.assertInstanceOf(BankAccount.class, accountProvider.get(0).get());
        Assertions.assertEquals("a", accountProvider.get(0).get().getName());
        Assertions.assertEquals(0, accountProvider.get(0).get().getId());
        Assertions.assertEquals(0, accountProvider.get(0).get().getBalance());

        var accMock2 = Mockito.mock(BankAccount.class);
        Mockito.when(accMock2.getId()).thenReturn(9999999);
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountProvider.save(accMock2));
    }

    @Test
    @DisplayName("Test category provider add consistency")
    void testInMemoryCategoryConsistency() {
        var catMock = Mockito.mock(Category.class);
        Mockito.when(catMock.getId()).thenReturn(0);
        Mockito.when(catMock.getName()).thenReturn("a");
        Mockito.when(catMock.getType()).thenReturn(OperationType.INCOME);
        categoryProvider.add(catMock);
        Assertions.assertTrue(categoryProvider.get(0).isPresent());
        Assertions.assertInstanceOf(Category.class, categoryProvider.get(0).get());
        Assertions.assertEquals("a", categoryProvider.get(0).get().getName());
        Assertions.assertEquals(0, categoryProvider.get(0).get().getId());
        Assertions.assertEquals(OperationType.INCOME, categoryProvider.get(0).get().getType());

        var catMock2 = Mockito.mock(Category.class);
        Mockito.when(catMock2.getId()).thenReturn(999999);
        Assertions.assertThrows(IllegalArgumentException.class, () -> categoryProvider.save(catMock2));
    }

    @Test
    @DisplayName("Test operation provider add consistency")
    void testInMemoryOperationConsistency() {
        var opMock = Mockito.mock(Operation.class);
        Mockito.when(opMock.getId()).thenReturn(0);
        Mockito.when(opMock.getDate()).thenReturn(new Date(0));
        Mockito.when(opMock.getType()).thenReturn(OperationType.INCOME);
        Mockito.when(opMock.getAmount()).thenReturn(1.0);
        Mockito.when(opMock.getBankAccountId()).thenReturn(1);
        Mockito.when(opMock.getCategoryId()).thenReturn(2);
        Mockito.when(opMock.getDescription()).thenReturn("a");
        operationProvider.add(opMock);
        Assertions.assertTrue(operationProvider.get(0).isPresent());
        Assertions.assertInstanceOf(Operation.class, operationProvider.get(0).get());
        Assertions.assertEquals("a", operationProvider.get(0).get().getDescription());
        Assertions.assertEquals(0, operationProvider.get(0).get().getId());
        Assertions.assertEquals(OperationType.INCOME, operationProvider.get(0).get().getType());
        Assertions.assertEquals(1.0, operationProvider.get(0).get().getAmount());
        Assertions.assertEquals(1, operationProvider.get(0).get().getBankAccountId());
        Assertions.assertEquals(2, operationProvider.get(0).get().getCategoryId());
        Assertions.assertEquals(new Date(0), operationProvider.get(0).get().getDate());

        var opMock2 = Mockito.mock(Operation.class);
        Mockito.when(opMock2.getId()).thenReturn(9999999);
        Assertions.assertThrows(IllegalArgumentException.class, () -> operationProvider.save(opMock2));
    }


}
