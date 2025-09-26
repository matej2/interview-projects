package com.doctor.file_processor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
abstract class DocumentValidator<T> {
    private final ObjectMapper objectMapper;
    private final Validator validator;
    private Class<T> clazz;

    public DocumentValidator(ObjectMapper objectMapper, Validator validator) {
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    protected Set<ConstraintViolation<T>> validateDocument(String resourceBody) throws JsonProcessingException {
        T validatedInputDocument = objectMapper.readValue(resourceBody, clazz);
        return validator.validate(validatedInputDocument);
    }

}
