package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRegisterForm;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<PatientResponseDto> getPatients() {
        return patientRepository.findAll().stream()
                .map(PatientResponseDto::toDto).toList();
    }

    public String registerPatient(PatientRegisterForm form) {
        var patient = PatientRegisterForm.toEntity(form);
        patient.setRegisterDate(LocalDate.now());
        patientRepository.save(patient);
        return patient.getName() + " registered successfully!";
    }


}
