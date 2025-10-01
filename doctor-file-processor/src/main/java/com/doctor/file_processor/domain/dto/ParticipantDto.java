package com.doctor.file_processor.domain.dto;

import com.doctor.file_processor.domain.ConfirmationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public record ParticipantDto(
        @Id
        Integer participantId,

        @NotBlank
        String role,

        @NotNull
        ConfirmationStatus confirmationStatus
){}
