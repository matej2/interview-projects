package com.doctor.file_processor.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record Patient(
        @NotNull
        int id,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotEmpty
        ArrayList<String> diseases
) {
}
