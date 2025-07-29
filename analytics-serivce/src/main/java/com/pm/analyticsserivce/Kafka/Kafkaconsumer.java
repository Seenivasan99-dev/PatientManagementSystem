package com.pm.analyticsserivce.Kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class Kafkaconsumer {
    @KafkaListener(topics = "patient",groupId = "analytics-service")
    public void ConsumerEvent(byte[] event) throws InvalidProtocolBufferException {
        PatientEvent patientevent=PatientEvent.parseFrom(event);
        System.out.println(patientevent.toString());
    }
}
