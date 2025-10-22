package com.doctor.file_processor.service.scheduler;

import com.doctor.file_processor.service.FileProcessorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentProcessorScheduledServiceTest {
    @Mock
    private FileProcessorService fileProcessorService;
    @Mock
    private Validator validator;
    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private DocumentProcessorScheduledService documentProcessorScheduledService;


    @Test
    void getDoctorFiles() throws IOException {
        when(fileProcessorService.getResources()).thenReturn(new FileSystemResource())
    }
}