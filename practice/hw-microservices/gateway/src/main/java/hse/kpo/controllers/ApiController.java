package hse.kpo.controllers;

import hse.kpo.services.ProxyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@Tag(name = "File Gateway API", description = "Proxy for file operations")
public class ApiController {
    @Autowired
    private ProxyService proxyService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload a file", description = "Accepts a file and forwards it to the remote service, returns uploaded file ID")
    public ResponseEntity<Long> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return proxyService.forwardFileUpload(file);
    }

    @PostMapping("/{id}/analyze")
    @Operation(summary = "Analyze a file", description = "Forwards an analysis request for the file with given ID")
    public ResponseEntity<String> analyze(@PathVariable Long id) {
        return proxyService.forwardAnalyze(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get file", description = "Fetch a file by ID")
    public ResponseEntity<Resource> getFile(@PathVariable Long id) {
        return proxyService.forwardGetFile(id);
    }

    @GetMapping("/wordcloud/{id}")
    @Operation(summary = "Get wordcloud image", description = "Get wordcloud of a previously analyzed file")
    public ResponseEntity<Resource> getWordcloud(@PathVariable Long id) {
        return proxyService.forwardGetWordcloud(id);
    }
}
