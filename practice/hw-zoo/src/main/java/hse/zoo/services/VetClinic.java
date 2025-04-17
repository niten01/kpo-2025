package hse.zoo.services;

import hse.zoo.domain.Animal;
import org.springframework.stereotype.Component;

/**
 * Animal health checking service
 */
@Component
class VetClinic {
    public boolean checkHealth(Animal animal) {
        return true;
    }
}