package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.domains.Report;
import hse.kpo.factories.cars.HandCarFactory;
import hse.kpo.factories.cars.LevitationCarFactory;
import hse.kpo.factories.cars.PedalCarFactory;
import hse.kpo.factories.catamarans.HandCatamaranFactory;
import hse.kpo.factories.catamarans.LevitationCatamaranFactory;
import hse.kpo.factories.catamarans.PedalCatamaranFactory;
import hse.kpo.observers.ReportSalesObserver;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.storages.CarStorage;
import hse.kpo.storages.CatamaranStorage;
import hse.kpo.storages.CustomerStorage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Hse {
    ReportSalesObserver reportSalesObserver;

    HseCarService hseCarService;
    HseCatamaranService hseCatamaranService;

    CustomerStorage customerStorage;

    CarStorage carStorage;
    CatamaranStorage catamaranStorage;

    PedalCarFactory pedalCarFactory;
    HandCarFactory handCarFactory;
    LevitationCarFactory levitationCarFactory;

    PedalCatamaranFactory pedalCatamaranFactory;
    HandCatamaranFactory handCatamaranFactory;
    LevitationCatamaranFactory levitationCatamaranFactory;

    public Hse(ReportSalesObserver reportSalesObserver, HseCarService hseCarService, HseCatamaranService hseCatamaranService, CustomerStorage customerStorage, CarStorage carStorage, CatamaranStorage catamaranStorage, PedalCarFactory pedalCarFactory, HandCarFactory handCarFactory, LevitationCarFactory levitationCarFactory, PedalCatamaranFactory pedalCatamaranFactory, HandCatamaranFactory handCatamaranFactory, LevitationCatamaranFactory levitationCatamaranFactory) {
        this.reportSalesObserver = reportSalesObserver;
        this.hseCarService = hseCarService;
        this.hseCatamaranService = hseCatamaranService;
        this.hseCatamaranService.addObserver(this.reportSalesObserver);
        this.hseCarService.addObserver(this.reportSalesObserver);
        this.customerStorage = customerStorage;
        this.carStorage = carStorage;
        this.catamaranStorage = catamaranStorage;
        this.pedalCarFactory = pedalCarFactory;
        this.handCarFactory = handCarFactory;
        this.levitationCarFactory = levitationCarFactory;
        this.pedalCatamaranFactory = pedalCatamaranFactory;
        this.handCatamaranFactory = handCatamaranFactory;
        this.levitationCatamaranFactory = levitationCatamaranFactory;
    }

    public void addCustomer(String name, int legPower, int handPower, int iq) {
        customerStorage.addCustomer(Customer.builder().name(name).legPower(legPower).handPower(handPower).iq(iq).build());
    }

    public void addPedalCar(int pedalSize) {
        carStorage.addCar(pedalCarFactory, new PedalEngineParams(pedalSize));
    }

    public void addHandCar() {
        carStorage.addCar(handCarFactory, EmptyEngineParams.DEFAULT);
    }

    public void addFlyingCar() {
        carStorage.addCar(levitationCarFactory, EmptyEngineParams.DEFAULT);
    }

    public void addPedalCatamaran(int pedalSize) {
        catamaranStorage.addCatamaran(pedalCatamaranFactory, new PedalEngineParams(pedalSize));
    }

    public void addHandCatamaran() {
        catamaranStorage.addCatamaran(handCatamaranFactory, EmptyEngineParams.DEFAULT);
    }

    public void addFlyingCatamaran() {
        catamaranStorage.addCatamaran(levitationCatamaranFactory, EmptyEngineParams.DEFAULT);
    }

    public void sell() {
        hseCarService.sellCars();
        hseCatamaranService.sellCatamarans();
    }

    public Report generateReport() {
        return reportSalesObserver.buildReport();
    }
}
