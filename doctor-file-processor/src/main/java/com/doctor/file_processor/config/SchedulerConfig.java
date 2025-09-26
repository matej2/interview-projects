package com.doctor.file_processor.config;

import com.doctor.file_processor.model.Doctor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;

@Component
@Slf4j
public class SchedulerConfig {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String basePath = "src/main/resources";
    private final Path validOutputDir = Path.of(String.format("%s/%s", basePath, "valid"));
    private final Path invalidOutputDir = Path.of(String.format("%s/%s", basePath, "error"));

    PathMatchingResourcePatternResolver resolver;

    public SchedulerConfig(PathMatchingResourcePatternResolver resolver) throws IOException {
        this.resolver = resolver;
        checkOrCreateDirectories(validOutputDir, invalidOutputDir);
    }

    private Resource[] getResources() throws IOException {
        return resolver.getResources("files/input/*.json");
    }

    private String getResourceBody(Resource res) throws IOException {
        return new String(Files.readAllBytes(Paths.get(res.getFile().getPath())));
    }

    private void processDocument(Resource document, Path targetPath) throws IOException {
        if (document.getFilename() != null) {
            Path docPath = validOutputDir.resolve(document.getFilename());
            Files.move(document.getFile().toPath(), docPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private void processValidDocument(Resource document) throws IOException {
        processDocument(document, validOutputDir);
    }

    private void processInvalidDocument(Resource document) throws IOException {
        processDocument(document, invalidOutputDir);
    }

    private void checkOrCreateDirectories(Path validOutputDir, Path invalidOutputDir) throws IOException {
        if (!Files.exists(validOutputDir)) {
            Files.createDirectories(validOutputDir);
        }

        if (!Files.exists(invalidOutputDir)) {
            Files.createDirectories(invalidOutputDir);
        }
    }

    @Scheduled(cron = "0 * * * * *")
    public void getDoctorFiles() throws IOException {
        Resource[] doctorInputJson = getResources();
        String resourceBody;

        for (Resource resource: doctorInputJson) {
            if (resource.getFilename() == null) {
                continue;
            }
            log.info("Processing resource: {}", resource.getFilename());
            resourceBody = getResourceBody(resource);

            Doctor validatedInputDocument = objectMapper.readValue(resourceBody, Doctor.class);
            Set<ConstraintViolation<Doctor>> violations = validator.validate(validatedInputDocument);

            if (!violations.isEmpty()) {
                processValidDocument(resource);
            } else {
                processInvalidDocument(resource);
            }
        }
    }
}