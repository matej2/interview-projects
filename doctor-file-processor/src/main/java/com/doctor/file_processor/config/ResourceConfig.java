package com.doctor.file_processor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class ResourceConfig {
    @Bean
    public PathMatchingResourcePatternResolver getPathMatchingResourcePatternResolver() {
        return new PathMatchingResourcePatternResolver();
    }
}
