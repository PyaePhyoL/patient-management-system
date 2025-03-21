package com.pm.patientservice.dto;

import com.pm.patientservice.model.Patient;
import com.pm.patientservice.validator.CreatePatientValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PatientRegisterForm(
        @NotBlank(message = "Name is required")
        @Size(max=100, message = "Name cannot exceeded 100 characters")
        String name,
        @NotBlank(message = "Email is required", groups =  {CreatePatientValidationGroup.class})
        String email,
        @NotBlank(message = "Address is required")
        String address,
        LocalDate birthDate
) {

    public static Patient toEntity(PatientRegisterForm form) {
        return Patient.builder()
                .name(form.name)
                .email(form.email)
                .address(form.address)
                .birthDate(form.birthDate)
                .build();
    }

}
