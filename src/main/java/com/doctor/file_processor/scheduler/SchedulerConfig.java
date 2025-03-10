package com.doctor.file_processor.scheduler;

import com.doctor.file_processor.model.Doctor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;

@Component
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

    private static void rename(Path source, Path target) throws IOException {
        Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
    }

    @Scheduled(cron = "0 * * * * *")
    public void getDoctorFiles() throws IOException {
        Resource[] doctorInputJson = getResources();
        ObjectMapper objectMapper = new ObjectMapper();
        Path validOutputDir = Paths.get("src", "main", "resources", "files", "valid");
        Path invalidOutputDir = Paths.get("src", "main", "resources", "files", "error");

        // Create the target directory if it doesn't exist
        if (!Files.exists(validOutputDir)) {
            Files.createDirectories(validOutputDir);
        }

        if (!Files.exists(invalidOutputDir)) {
            Files.createDirectories(invalidOutputDir);
        }

        for (Resource resource: doctorInputJson) {
            Errors result = null;
            String resourceBody = getResourceBody(resource);

            Doctor doctorInput = objectMapper.readValue(resourceBody, Doctor.class);

            Set<ConstraintViolation<Doctor>> violations = validator.validate(doctorInput);

            Path validPath = validOutputDir.resolve(resource.getFilename());
            Path invalidPath = invalidOutputDir.resolve(resource.getFilename());

            if (result != null && result.hasErrors()) {
                rename(resource.getFile().toPath(), invalidPath);
            } else {
                rename(resource.getFile().toPath(), validPath);
            }
        }


    }
}