package com.doctor.file_processor.service;

import com.doctor.file_processor.domain.dto.DoctorDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
abstract class DocumentValidator {
    private final ObjectMapper objectMapper;
    private final Validator validator;

    public DocumentValidator(ObjectMapper objectMapper, Validator validator) {
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    protected Set<ConstraintViolation<DoctorDto>> validateDocument(String resourceBody) throws JsonProcessingException {
        DoctorDto validatedInputDocument = objectMapper.readValue(resourceBody, DoctorDto.class);
       return validator.validate(validatedInputDocument);
    }

}
