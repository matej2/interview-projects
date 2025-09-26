package com.doctor.file_processor.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class ResourceConfig {
    @Bean
    public PathMatchingResourcePatternResolver getPathMatchingResourcePatternResolver() {
        return new PathMatchingResourcePatternResolver();
    }
    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
