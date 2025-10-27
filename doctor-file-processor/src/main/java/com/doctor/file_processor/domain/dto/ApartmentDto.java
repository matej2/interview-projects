package com.doctor.file_processor.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;

public record ApartmentDto (
    @Id
    Integer id,
    @NotNull
    @NotEmpty
    String name,
    @NotNull
    String address,
    Boolean petsAllowed,
    Integer numberOfRooms,
    @NotNull
    Integer yearBuilt,
    @NotNull
    @Positive
    Integer squareMeters,
    @NotNull
    Long price
){}
