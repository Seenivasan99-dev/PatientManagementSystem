package com.pm.patientservice.service;

import com.pm.patientservice.Dto.PatientDto;
import com.pm.patientservice.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PatientService {
    public List<Patient> getAllPatients();
    public Patient getPatientByEmail(String email);
    public Patient createPatient(Patient patient);
    public Patient updatePatient(Patient patient,UUID id);
    public void deletePatient(UUID id);
}
