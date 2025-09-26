package com.doctor.file_processor.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PatientDto(
        @NotNull
        int id,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotEmpty
        List<String> diseases
) {
}
