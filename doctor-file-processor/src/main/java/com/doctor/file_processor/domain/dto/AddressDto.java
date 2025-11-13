package com.doctor.file_processor.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public record AddressDto(
        @Id
        Integer id,
        @NotNull
        @NotBlank
        String streetName,
        @NotNull
        @NotBlank
        String streetNumber,
        String streetNumberSecondary,
        @NotNull
        @NotBlank
        String postalCode,
        @NotNull
        @NotBlank
        String cityName,
        @NotNull
        @NotBlank
        String country
){}
