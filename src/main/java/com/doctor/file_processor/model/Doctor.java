package com.doctor.file_processor.model;

import java.util.ArrayList;

public record Doctor(
        int id,
        String department,
        ArrayList<Patient> patients
) {
}
