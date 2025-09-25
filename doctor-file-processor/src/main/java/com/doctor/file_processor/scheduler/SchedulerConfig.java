package com.doctor.file_processor.scheduler;

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

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();


    private Resource[] getResources() throws IOException {
        return resolver.getResources("files/input/*.json");

    }

    private String getResourceBody(Resource res) throws IOException {
        return new String(Files.readAllBytes(Paths.get(res.getFile().getPath())));
    }

    private static void move(Path source, Path target) throws IOException {
        Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
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
        ObjectMapper objectMapper = new ObjectMapper();
        Path validOutputDir = Paths.get("src", "main", "resources", "files", "valid");
        Path invalidOutputDir = Paths.get("src", "main", "resources", "files", "error");

        checkOrCreateDirectories(validOutputDir, invalidOutputDir);

        for (Resource resource: doctorInputJson) {
            if (resource == null || resource.getFilename() == null) {
                continue;
            }
            log.info("Processing resource: {}", resource.getFilename());

            String resourceBody = getResourceBody(resource);
            Path validPath = validOutputDir.resolve(resource.getFilename());
            Path invalidPath = invalidOutputDir.resolve(resource.getFilename());

            Doctor doctorInput = objectMapper.readValue(resourceBody, Doctor.class);
            Set<ConstraintViolation<Doctor>> violations = validator.validate(doctorInput);

            if (!violations.isEmpty()) {
                move(resource.getFile().toPath(), invalidPath);
            } else {
                move(resource.getFile().toPath(), validPath);
            }
        }


    }
}