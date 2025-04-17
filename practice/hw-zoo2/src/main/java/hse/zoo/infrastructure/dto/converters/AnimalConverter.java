package hse.zoo.infrastructure.dto.converters;

import hse.zoo.domain.Animal;
import hse.zoo.domain.enums.Gender;
import hse.zoo.domain.enums.Status;
import hse.zoo.domain.valueobjets.Food;
import hse.zoo.infrastructure.dto.AnimalDTO;
import hse.zoo.infrastructure.dto.request.CreateAnimalRequest;

public class AnimalConverter {
    public static AnimalDTO toDTO(Animal animal) {
        Integer enclosureID = animal.getEnclosure() != null ? animal.getEnclosure().getId().value() : null;
        return new AnimalDTO(animal.getNickname(), animal.getSpecies(), animal.getBirthDate(), animal.getGender().toString(), animal.getStatus().toString(), animal.getFavouriteFood().name(), enclosureID);
    }

    public static Animal fromRequest(CreateAnimalRequest request) {
        return new Animal(request.nickname, request.species, request.birthDate, Gender.valueOf(request.gender), new Food(request.favouriteFood), Status.valueOf(request.status));
    }
}
