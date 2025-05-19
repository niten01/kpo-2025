package hse.kpo.controllers;

import hse.kpo.persistence.StoredFileEntity;
import hse.kpo.services.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/storage")
@RequiredArgsConstructor
public class StorageController {
    private final FileStorageService service;

    @PostMapping("/upload")
    public ResponseEntity<Long> upload(@RequestParam("file") MultipartFile file) throws Exception {
        StoredFileEntity saved = service.store(file);
        return ResponseEntity.ok(saved.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileSystemResource> getFile(@PathVariable Long id) throws Exception {
        File file = service.retrieve(id);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                .body(new FileSystemResource(file));
    }
}
