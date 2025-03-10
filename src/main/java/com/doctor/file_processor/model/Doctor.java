package com.doctor.file_processor.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record Doctor(
        @NotNull
        int id,
        @NotBlank
        String department,
        ArrayList<Patient> patients
) {
}
