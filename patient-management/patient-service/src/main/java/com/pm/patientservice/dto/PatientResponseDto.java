package com.pm.patientservice.dto;

import com.pm.patientservice.model.Patient;

public record PatientResponseDto(
        String id,
        String name,
        String email,
        String address,
        String dateOfBirth
) {

    public static PatientResponseDto toDto(Patient patient) {
        return new PatientResponseDto(
                patient.getId().toString(),
                patient.getName(),
                patient.getEmail(),
                patient.getAddress(),
                patient.getBirthDate().toString()
        );
    }
}
