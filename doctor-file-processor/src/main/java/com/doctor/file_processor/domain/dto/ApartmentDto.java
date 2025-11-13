package com.doctor.file_processor.domain.dto;

import com.doctor.file_processor.domain.ApartmentType;
import com.doctor.file_processor.domain.TransactionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;

import java.util.List;

public record ApartmentDto (
    @Id
    Integer id,
    @NotNull
    @NotEmpty
    String name,
    @NotNull
    AddressDto address,
    Boolean petsAllowed,
    Integer numberOfRooms,
    @NotNull
    Integer yearBuilt,
    @NotNull
    @Positive
    Integer squareMeters,
    @NotNull
    List<OwnerDto> owners,
    TransactionType typeOfTransaction,
    ApartmentType typeOfApartment,
    @NotNull
    Long price
){}
