package com.doctor.file_processor.controller;

import com.doctor.file_processor.domain.dto.EventDto;
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

    @PostMapping(value = "/file", consumes = "application/json")
    public ResponseEntity<String> sendFile(
            @RequestBody @Valid EventDto request
            ) {
        return new ResponseEntity<>(request.toString(), HttpStatus.OK);
    }
}
