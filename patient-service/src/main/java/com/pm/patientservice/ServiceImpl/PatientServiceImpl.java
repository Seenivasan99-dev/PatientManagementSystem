package com.pm.patientservice.ServiceImpl;

import com.pm.patientservice.Exceotions.PatientNotFoundException;
import com.pm.patientservice.Grpc.BillingServiceGrpcClient;
import com.pm.patientservice.Repository.PatientRepository;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.service.PatientService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.pm.patientservice.kafka.kafkaProducer;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patrepo;

    BillingServiceGrpcClient billingServiceGrpcClient;

    kafkaProducer kafkaProducer;


    public PatientServiceImpl(PatientRepository patrepo, BillingServiceGrpcClient billingServiceGrpcClient, kafkaProducer kafkaProducer) {
        this.patrepo = patrepo;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patient = patrepo.findAll();
        return patient;
    }

    @Override
    public Patient getPatientByEmail(String email) {
        Optional.ofNullable(patrepo.findByEmail(email)).orElseThrow(()->new PatientNotFoundException("404","Patient Not Found",LocalDate.now().toString()));
        Patient pat=patrepo.findByEmail(email);
        return pat;
    }


    @Override
    public Patient createPatient(Patient patient) {
        Patient newpatient=patrepo.save(patient);
        billingServiceGrpcClient.createBillingAccount(newpatient.getId().toString(),newpatient.getName(),newpatient.getEmail());
        kafkaProducer.sendEvent(newpatient);
        return newpatient;
    }

    @Override
    public Patient updatePatient(Patient patient, UUID id) {
        Patient oldpatient=patrepo.findById(id).orElseThrow(()->new PatientNotFoundException("404","Patient Not Found",LocalDate.now().toString()));
        oldpatient.setName(patient.getName());
        oldpatient.setAddress(patient.getAddress());
        oldpatient.setEmail(patient.getEmail());
        oldpatient.setDateofBirth(patient.getDateofBirth());
        oldpatient.setRegisteredDate(patient.getRegisteredDate());
        patrepo.save(oldpatient);
        return oldpatient;
    }

    @Override
    public void deletePatient(UUID id) {
        Patient patient =patrepo.findById(id).orElseThrow(()->new PatientNotFoundException("404","Patient Not Found",LocalDate.now().toString()));
        if(patient.getId().toString().length()<12 || patient.getId()==null){
            throw new PatientNotFoundException("404","Patient Not Found",LocalDate.now().toString());
        }
        patrepo.delete(patient);
    }
}
