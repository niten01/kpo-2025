package hse.kpo;

import hse.kpo.reports.ReportBuilder;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point class.
 */
@SpringBootApplication
public class KpoApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(KpoApplication.class, args);
        var hseCarService = context.getBean(HseCarService.class);
        var customerStorage = context.getBean(CustomerStorage.class);

        var reportBuilder = new ReportBuilder()
                .addOperation("Инициализация системы")
                .addCustomers(customerStorage.getCustomers());

        hseCarService.sellCars();

        var report = reportBuilder
                .addOperation("Продажа автомобилей")
                .addCustomers(customerStorage.getCustomers())
                .build();

        System.out.println(report.toString());
    }
}
