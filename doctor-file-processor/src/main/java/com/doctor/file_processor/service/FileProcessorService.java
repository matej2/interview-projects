package com.doctor.file_processor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class FileProcessorService {
    private static final Logger log = LoggerFactory.getLogger(FileProcessorService.class);
    private final Path basePath = Path.of("D:", "apartment");
    private final Path validOutputDir = Path.of(basePath.toString(), "valid");
    private final Path invalidOutputDir = Path.of(basePath.toString(), "invalid");
    private final Path inputDir = Path.of(basePath.toString(), "input");

    public FileProcessorService() throws IOException {
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

    public String getResourceBody(File res) throws IOException {
        return Files.readString(res.toPath(), StandardCharsets.UTF_8);
    }

    public Set<File> getResources() throws IOException {
        try (Stream<Path> stream = Files.list(inputDir)) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(p -> new File(p.toUri()))
                    .collect(Collectors.toSet());
        }
    }


    private void processDocument(File document, Path targetPath) throws IOException {
        if (!document.getName().isEmpty()) {
            Path fullTargetPath = targetPath.resolve(document.getName());

            try {
                Files.move(document.toPath(), fullTargetPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (AccessDeniedException e) {
                log.warn(e.toString());
            }
        }
    }

    public void processValidDocument(File document) throws IOException {
        processDocument(document, validOutputDir);
    }

    public void processInvalidDocument(File document) throws IOException {
        processDocument(document, invalidOutputDir);
    }

}
