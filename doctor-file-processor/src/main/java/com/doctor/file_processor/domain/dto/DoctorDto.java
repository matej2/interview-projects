package com.doctor.file_processor.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record DoctorDto(
        @NotNull
        int id,
        @NotBlank
        String department,
        ArrayList<PatientDto> patients
) {
}
