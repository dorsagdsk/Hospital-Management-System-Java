package com.hospital.exception;

public class DuplicatePatientException extends RuntimeException {

    public DuplicatePatientException(String message) {
        super(message);
    }
}
