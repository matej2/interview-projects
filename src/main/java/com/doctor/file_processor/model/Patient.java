package com.doctor.file_processor.model;

import java.util.ArrayList;

public record Patient(
        int id,
        String firstName,
        String lastName,
        ArrayList<String> diseases
) {
}
