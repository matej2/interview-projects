package com.doctor.file_processor.domain.dto;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public record EventDto(
        @Id
        Integer id,

        @NotBlank
        String title,

        @NotNull
        @FutureOrPresent
        Date startTime,

        @NotNull
        @Future
        Date endTime,

        @NotEmpty
        List<Participant> participantList
){}
