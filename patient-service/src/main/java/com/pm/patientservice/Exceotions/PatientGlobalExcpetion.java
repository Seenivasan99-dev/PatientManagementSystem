package com.pm.patientservice.Exceotions;

import com.pm.patientservice.ErrorResponse.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class PatientGlobalExcpetion {

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String, Object>> patientGlobalExcpetion(PatientNotFoundException patientException) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", patientException.getTimeStamp().toString());
        errorDetails.put("message", patientException.getMessage());
        errorDetails.put("errorCode", patientException.getErrorCode());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String,Object>> nosuchException(PatientNotFoundException patientException) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", patientException.getTimeStamp().toString());
        errorDetails.put("message", patientException.getMessage());
        errorDetails.put("errorCode", patientException.getErrorCode());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }
}
