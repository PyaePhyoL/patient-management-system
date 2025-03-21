package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRegisterForm;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.service.PatientService;
import com.pm.patientservice.validator.CreatePatientValidationGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    @Operation(summary = "Register New Patient")
    public ResponseEntity<String> registerPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRegisterForm form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.registerPatient(form));
    }


    @GetMapping
    @Operation(summary = "Get All Patients")
    public ResponseEntity<List<PatientResponseDto>> getPatients() {
        return ResponseEntity.ok().body(patientService.getPatients());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit Patient")
    public ResponseEntity<PatientResponseDto> updatePatient(@Validated({Default.class}) @RequestBody PatientRegisterForm form,
                                                            @PathVariable UUID id) {
        return ResponseEntity.ok().body(patientService.updatePatient(form, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Patient by ID")
    public ResponseEntity<String> deletePatient(@PathVariable UUID id) {
        return ResponseEntity.ok().body(patientService.deletePatient(id));
    }
}
