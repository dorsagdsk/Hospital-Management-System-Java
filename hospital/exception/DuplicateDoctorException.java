package com.hospital.exception;

public class DuplicateDoctorException extends RuntimeException {
    public DuplicateDoctorException(String message) {
        super(message);
    }
}
