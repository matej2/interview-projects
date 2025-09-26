package com.doctor.file_processor.service;

import com.doctor.file_processor.domain.dto.EventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jdk.jfr.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
@Slf4j
public class SchedulerService extends DocumentValidator<EventDto> {

    private final String basePath = "src/main/resources";
    private final FileProcessorService fileProcessorService;
    private final Validator validator;
    private final PathMatchingResourcePatternResolver resolver;
    private final ObjectMapper objectMapper;

    public SchedulerService(FileProcessorService fileProcessorService, PathMatchingResourcePatternResolver resolver, Validator validator, ObjectMapper objectMapper) throws IOException {
        super(objectMapper, validator);
        this.fileProcessorService = fileProcessorService;
        this.validator = validator;
        this.resolver = resolver;
        this.objectMapper = objectMapper;
    }

    @Scheduled(cron = "0 * * * * *")
    public void getDoctorFiles() throws IOException {
        Resource[] doctorInputJson = fileProcessorService.getResources();
        String resourceBody;

        for (Resource resource: doctorInputJson) {
            if (resource.getFilename() == null) {
                continue;
            }
            log.info("Processing resource: {}", resource.getFilename());
            resourceBody = fileProcessorService.getResourceBody(resource);

            Set<ConstraintViolation<EventDto>> violations = validateDocument(resourceBody);

            if (!violations.isEmpty()) {
                fileProcessorService.processValidDocument(resource);
            } else {
                fileProcessorService.processInvalidDocument(resource);
            }
        }
    }
}