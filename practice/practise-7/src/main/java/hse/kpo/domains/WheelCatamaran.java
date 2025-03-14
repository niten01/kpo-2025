package hse.kpo.domains;

public class WheelCatamaran extends Car {
    public WheelCatamaran(Catamaran catamaran) {
        super(catamaran.getVin(), catamaran.getEngine());
    }
}
