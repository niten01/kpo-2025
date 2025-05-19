package hse.zoo.presentation.controllers;

import hse.zoo.application.interfaces.EnclosureRepository;
import hse.zoo.domain.Enclosure;
import hse.zoo.domain.valueobjets.EnclosureID;
import hse.zoo.infrastructure.dto.EnclosureDTO;
import hse.zoo.infrastructure.dto.converters.EnclosureConverter;
import hse.zoo.infrastructure.dto.request.CreateEnclosureRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/enclosures")
@RequiredArgsConstructor
public class EnclosureController {
    private final EnclosureRepository enclosureRepository;

    @GetMapping
    public List<EnclosureDTO> getAllEnclosures() {
        return enclosureRepository.getAllEnclosures().stream().map(EnclosureConverter::toDTO).toList();
    }

    @GetMapping("/{id}")
    public EnclosureDTO getEnclosure(@PathVariable int id) {
        Enclosure enclosure = enclosureRepository.findEnclosure(EnclosureID.create(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enclosure not found"));
        return EnclosureConverter.toDTO(enclosure);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnclosureDTO createEnclosure(@Valid @RequestBody CreateEnclosureRequest request) {
        Enclosure newEnclosure = EnclosureConverter.fromRequest(request);
        enclosureRepository.addEnclosure(newEnclosure);
        return EnclosureConverter.toDTO(newEnclosure);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEnclosure(@PathVariable int id) {
        Enclosure enclosure = enclosureRepository.findEnclosure(EnclosureID.create(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enclosure not found"));
        enclosureRepository.removeEnclosure(enclosure);
    }
}
