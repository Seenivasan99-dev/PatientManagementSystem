package com.pm.patientservice.kafka;

import com.pm.patientservice.model.Patient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class kafkaProducer {


    private final KafkaTemplate<String,byte[]> kafkaTemplate;


    public kafkaProducer(KafkaTemplate<String,byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Patient patient) {
        PatientEvent event=PatientEvent.newBuilder().setPatientId(patient.getId().toString()).setName(patient.getName()).setEmail(patient.getEmail()).setEventType("PATIENT_CREATED").build();
        try{
            kafkaTemplate.send("patient",event.toByteArray());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }




}
