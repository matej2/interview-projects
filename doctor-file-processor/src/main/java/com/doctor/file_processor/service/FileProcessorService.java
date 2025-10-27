package com.doctor.file_processor.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Controller
public class FileProcessorService {
    private final String basePath = "src/main/resources";
    private final Path validOutputDir = Path.of(String.format("%s/%s", basePath, "valid"));
    private final Path invalidOutputDir = Path.of(String.format("%s/%s", basePath, "error"));
    private final PathMatchingResourcePatternResolver resolver;

    public FileProcessorService(PathMatchingResourcePatternResolver resolver) throws IOException {
        this.resolver = resolver;
        checkOrCreateDirectories();
    }

    private void checkOrCreateDirectories() throws IOException {
        if (!Files.exists(validOutputDir)) {
            Files.createDirectories(validOutputDir);
        }

        if (!Files.exists(invalidOutputDir)) {
            Files.createDirectories(invalidOutputDir);
        }
    }

    public Resource[] getResources() throws IOException {
        return resolver.getResources("files/input/*.json");
    }

    public String getResourceBody(Resource res) throws IOException {
        return Files.readString(res.getFile().toPath(), StandardCharsets.UTF_8);
    }

    private void processDocument(Resource document, Path targetPath) throws IOException {
        if (document.getFilename() != null) {
            Path docPath = validOutputDir.resolve(document.getFilename());
            Files.move(document.getFile().toPath(), docPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public void processValidDocument(Resource document) throws IOException {
        processDocument(document, validOutputDir);
    }

    public void processInvalidDocument(Resource document) throws IOException {
        processDocument(document, invalidOutputDir);
    }

}
