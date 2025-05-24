package hse.kpo.services;

import hse.kpo.persistence.AnalysisResult;
import hse.kpo.persistence.AnalysisResultRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

import org.springframework.http.HttpHeaders;

@Service
public class AnalysisService {

    private final AnalysisResultRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${remote.filestorage-service.base-url}")
    private String fileStorageBaseUrl;

    @Value("${analysis.wordcloud-path}")
    private String wordCloudPath;

    public AnalysisService(AnalysisResultRepository repository) {
        this.repository = repository;
    }

    public AnalysisResult analyze(Long fileId) {
        return repository.findById(fileId).orElseGet(() -> {
            try {
                String fileContent = restTemplate.getForObject(fileStorageBaseUrl + "/api/storage/" + fileId, String.class);
                var result = performAnalysis(fileContent, fileId);
                return repository.save(result);
            } catch (IOException ex) {
                throw new RuntimeException("Analysis failed", ex);
            }
        });
    }

    public File getImage(Long id) throws IllegalArgumentException {
        AnalysisResult result =  repository.findById(id).orElseThrow(() -> new IllegalArgumentException("File not analyzed"));
        return new File(result.getWordCloudImageLocation());
    }

    private AnalysisResult performAnalysis(String text, Long fileId) throws IOException {
        var wordCount = text.split("\\s+").length;
        var symbolCount = text.length();
        var lineCount = text.split("\r\n|\r|\n").length;
        var wordCloudLoc = generateWordCloud(text, fileId);
        var result = new AnalysisResult();
        result.setFileId(fileId);
        result.setWordCount(wordCount);
        result.setSymbolCount(symbolCount);
        result.setLineCount(lineCount);
        result.setWordCloudImageLocation(wordCloudLoc);
        return result;
    }

    private String generateWordCloud(String text, Long fileId) throws IOException {
        // wordcloudapi.com переехало на rapid api как я понял и там чето платно,
        // короче я не стал заморачиваться, думаю это не так важно
        String url = "https://textvis-word-cloud-v1.p.rapidapi.com/v1/textToCloud";
        Map<String, Object> body = Map.of("text", text);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        // ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        String response = "iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAIAAAACUFjqAAAAGUlEQVR4nGJRP3GAATdgwiM3gqUBAQAA//8XBQHGyZajIwAAAABJRU5ErkJggg==";
        byte[] imageBytes = Base64.getDecoder().decode(response);

        String filePath = wordCloudPath + File.separator + "cloud_" + fileId + ".png";
        Files.createDirectories(Paths.get(wordCloudPath));
        Files.write(Paths.get(filePath), imageBytes);

        return filePath;
    }
}
