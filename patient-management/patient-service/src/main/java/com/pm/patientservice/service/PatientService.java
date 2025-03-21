package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRegisterForm;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<PatientResponseDto> getPatients() {
        return patientRepository.findAll().stream()
                .map(PatientResponseDto::toDto).toList();
    }

    public String registerPatient(PatientRegisterForm form) {

        if(patientRepository.existsByEmail(form.email())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists : " + form.email());
        }

        var patient = PatientRegisterForm.toEntity(form);
        patient.setRegisterDate(LocalDate.now());
        patientRepository.save(patient);
        return patient.getName() + " registered successfully!";
    }

    public PatientResponseDto updatePatient(PatientRegisterForm form, UUID id) {
        var patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with id " + id + " not found!"));

        if(patientRepository.existsByEmailAndIdNot(form.email(), id)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists : " + form.email());
        }

        patient.setName(form.name());
        patient.setEmail(form.email());
        patient.setAddress(form.address());
        patient.setBirthDate(form.birthDate());
        return PatientResponseDto.toDto(patientRepository.save(patient));
    }

    public String deletePatient(UUID id) {
        if(!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient with id " + id + " not found!");
        }
        patientRepository.deleteById(id);
        return "Patient with id " + id + " has been deleted successfully!";
    }


}
