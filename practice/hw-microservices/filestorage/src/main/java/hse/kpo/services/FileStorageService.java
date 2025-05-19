package hse.kpo.services;

import hse.kpo.repositories.StoredFileEntity;
import hse.kpo.repositories.StoredFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class FileStorageService {
    private final StoredFileRepository repository;

    @Value("${storage.base-path}")
    private String basePath;

    public StoredFileEntity store(MultipartFile file) throws IOException {
        StoredFileEntity entity = new StoredFileEntity();
        entity.setName(file.getOriginalFilename());
        repository.save(entity); // save first to get id

        String location = basePath + File.separator + entity.getId() + "_" + file.getOriginalFilename();
        Path path = Paths.get(location);

        Files.createDirectories(path.getParent());
        Files.copy(file.getInputStream(), path);

        entity.setLocation(location);
        return repository.save(entity);
    }

    public File retrieve(Long id) throws Exception {
        StoredFileEntity file = repository.findById(id)
                .orElseThrow(() -> new Exception("File not found"));
        return new File(file.getLocation());
    }
}
