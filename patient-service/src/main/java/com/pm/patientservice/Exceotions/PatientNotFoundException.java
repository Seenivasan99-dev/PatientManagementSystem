package com.pm.patientservice.Exceotions;

public class PatientNotFoundException extends RuntimeException {
    private String errorCode;
    private String errorMessage;
    private String timeStamp;
    public PatientNotFoundException(String  errorCode, String errorMessage,String timeStamp) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timeStamp = timeStamp;

    }
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
