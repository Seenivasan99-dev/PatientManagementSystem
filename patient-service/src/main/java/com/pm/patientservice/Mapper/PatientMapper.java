package com.pm.patientservice.Mapper;

import com.pm.patientservice.Dto.PatientDto;
import com.pm.patientservice.model.Patient;

public class PatientMapper {

    public static PatientDto PatientToPatientDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setAddress(patient.getAddress());
        patientDto.setEmail(patient.getEmail());
        patientDto.setDateOfBirth(patient.getDateofBirth());
        patientDto.setRegisterDate(patient.getRegisteredDate());

        return patientDto;
    }

    public static Patient PatientDtoToPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setId(patientDto.getId());
        patient.setName(patientDto.getName());
        patient.setAddress(patientDto.getAddress());
        patient.setEmail(patientDto.getEmail());
        patient.setDateofBirth(patientDto.getDateOfBirth());
        patient.setRegisteredDate(patientDto.getRegisterDate());
        return patient;
    }
}
