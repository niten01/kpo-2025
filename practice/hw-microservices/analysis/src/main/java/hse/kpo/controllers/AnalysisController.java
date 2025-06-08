package hse.kpo.controllers;

import hse.kpo.persistence.AnalysisResult;
import hse.kpo.services.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService service;

    @GetMapping("/{id}")
    public ResponseEntity<AnalysisResult> analyze(@PathVariable Long id) {
        AnalysisResult result = service.analyze(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/wordcloud/{id}")
    public ResponseEntity<FileSystemResource> getImage(@PathVariable Long id) {
        File file = service.getImage(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"").body(new FileSystemResource(file));
    }
}
