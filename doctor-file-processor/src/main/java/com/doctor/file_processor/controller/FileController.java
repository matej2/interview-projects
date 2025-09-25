package com.doctor.file_processor.controller;

import com.doctor.file_processor.model.Doctor;
import com.doctor.file_processor.model.Patient;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Controller
public class FileController {

    @PostMapping(value = "/file", consumes = "application/json")
    public ResponseEntity<String> sendFile(
            @RequestBody @Valid Doctor request
            ) {
        return new ResponseEntity<>(request.toString(), HttpStatus.OK);
    }
}
