package com.doctor.file_processor.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {

    @Bean
    public ValidatorFactory getValidatorFactory() {
        return Validation.buildDefaultValidatorFactory();
    }

    @Bean
    public Validator getValidator() {
        return getValidatorFactory().getValidator();
    }
}
