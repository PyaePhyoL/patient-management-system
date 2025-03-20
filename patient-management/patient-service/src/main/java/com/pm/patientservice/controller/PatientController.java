package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRegisterForm;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<String> registerPatient(@Valid @RequestBody PatientRegisterForm form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.registerPatient(form));
    }


    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getPatients() {
        return ResponseEntity.ok().body(patientService.getPatients());
    }
}
