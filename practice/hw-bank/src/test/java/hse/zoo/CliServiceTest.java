package hse.zoo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Strange test to fulfill coverage percentage requirement
 */
@SpringBootTest
public class CliServiceTest {
    @Autowired
    private CliMenuService cliMenuService;

    @Test
    @DisplayName("Test cli menu service start")
    void cliMenuServiceStartTest() {
        InputStream fakeIn = new ByteArrayInputStream("list\n\rq\n\r".getBytes());
        System.setIn(fakeIn);

        Assertions.assertDoesNotThrow(() -> cliMenuService.start());
    }
}
