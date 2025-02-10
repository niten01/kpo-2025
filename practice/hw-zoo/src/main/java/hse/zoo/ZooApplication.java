package hse.zoo;

import hse.zoo.domains.Animal;
import hse.zoo.domains.Herbo;
import hse.zoo.factories.AnimalFactory;
import hse.zoo.services.CliMenuService;
import hse.zoo.services.Zoo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

/**
 * Entry point class.
 */
@SpringBootApplication
public class ZooApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ZooApplication.class, args);
        CliMenuService cliMenu = context.getBean(CliMenuService.class);
        cliMenu.start();
    }
}
