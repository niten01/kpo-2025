package hse.kpo.domains;

import hse.kpo.interfaces.Engine;

public class WheeledCatamaran extends Car {
    public WheeledCatamaran(Catamaran catamaran) {
        super(catamaran.getVin(), catamaran.getEngine());
    }
}
