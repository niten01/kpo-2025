package hse.zoo.presentation.controllers;

import hse.zoo.application.services.AnimalTransferService;
import hse.zoo.domain.valueobjets.EnclosureID;
import hse.zoo.exceptions.ZooException;
import hse.zoo.infrastructure.dto.request.MoveAnimalRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class AnimalTransferController {
    private final AnimalTransferService animalTransferService;

    @PostMapping
    public void transferAnimal(@Valid @RequestBody MoveAnimalRequest request) {
        try {
            animalTransferService.transferAnimal(request.animalNickname, EnclosureID.create(request.newEnclosureID));
        } catch (ZooException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

}
