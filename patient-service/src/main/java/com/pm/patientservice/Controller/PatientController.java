package com.pm.patientservice.Controller;


import com.pm.patientservice.Dto.PatientDto;
import com.pm.patientservice.Exceotions.PatientNotFoundException;
import com.pm.patientservice.Mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/Patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/All/Patients")
    public ResponseEntity<List<PatientDto>> getAllPatient(){
        List<Patient> patient=patientService.getAllPatients();
        List<PatientDto> patientDtos= patient.stream().map(PatientMapper::PatientToPatientDto).toList();
        ResponseEntity<List<PatientDto>> response= ResponseEntity.ok().body(patientDtos);
        if(HttpStatus.OK.equals(response.getStatusCode())){
            return response;
        }
        else {
            return new ResponseEntity<>(response.getStatusCode());
        }
    }
    @GetMapping("/Patient/get")
    public ResponseEntity<PatientDto> getPatientById(@RequestParam String email) throws PatientNotFoundException {
        Patient patient=patientService.getPatientByEmail(email);
        PatientDto patdto=PatientMapper.PatientToPatientDto(patient);
        ResponseEntity<PatientDto> response= ResponseEntity.ok().body(patdto);
        if(HttpStatus.OK.equals(response.getStatusCode())) {
            return response;
        }
        return new ResponseEntity<>(response.getStatusCode());
    }
    @PostMapping("/Patient/Create")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient){
        Patient createdpatient=patientService.createPatient(patient);
        ResponseEntity<Patient> response= ResponseEntity.ok().body(createdpatient);
        if(HttpStatus.CREATED.equals(response.getStatusCode())){
            return response;
        }
        else {
            return new ResponseEntity<>(response.getStatusCode());
        }
    }
    @PutMapping("/Patient/Update/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient,@PathVariable UUID id){
        Patient updatedpatient=patientService.updatePatient(patient, id);
        ResponseEntity.ok(updatedpatient);
        ResponseEntity<Patient> response= ResponseEntity.ok().body(updatedpatient);
        if(HttpStatus.OK.equals(response.getStatusCode())){
            return response;
        }
        else {
            return new ResponseEntity<>(response.getStatusCode());
        }
    }
    @DeleteMapping("/Patient/delete/{email}")
    public void deletePatient(@PathVariable String email){
        Patient pt=patientService.getPatientByEmail(email);
        patientService.deletePatient(pt.getId());

    }



}
