package com.doctor.file_processor.service.scheduler;

import com.doctor.file_processor.domain.dto.EventDto;
import com.doctor.file_processor.service.DocumentValidator;
import com.doctor.file_processor.service.FileProcessorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
@Slf4j
public class DocumentProcessorScheduledService extends DocumentValidator<EventDto> {

    private final FileProcessorService fileProcessorService;

    public DocumentProcessorScheduledService(FileProcessorService fileProcessorService, Validator validator, ObjectMapper objectMapper) {
        super(objectMapper, validator);
        this.fileProcessorService = fileProcessorService;
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

            Set<ConstraintViolation<EventDto>> violations = validateDocument(resourceBody, EventDto.class);

            if (!violations.isEmpty()) {
                fileProcessorService.processValidDocument(resource);
            } else {
                fileProcessorService.processInvalidDocument(resource);
            }
        }
    }
}