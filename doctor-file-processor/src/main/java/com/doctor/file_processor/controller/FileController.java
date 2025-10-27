package com.doctor.file_processor.controller;

import com.doctor.file_processor.domain.dto.ApartmentDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api")
@Controller
public class FileController {
    @PostMapping(value = "/apartment", consumes = "application/json")
    public ResponseEntity<String> sendFile(
            @RequestBody @Valid ApartmentDto request
    ) {
        return new ResponseEntity<>(request.toString(), HttpStatus.OK);
    }
}
