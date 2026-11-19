package com.hospital.model;

import com.hospital.repository.Identifiable;

import java.time.LocalDateTime;
import java.util.Objects;

public class Appointment implements Identifiable {


    private int id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;
    private AppointmentStatus status;

    public Appointment(int id ,Patient patient, Doctor doctor, AppointmentStatus status, LocalDateTime dateTime) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.status = status;
        this.dateTime = dateTime;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                ",id=" + id +
                "patient=" + patient +
                ", doctor=" + doctor +
                ", dateTime=" + dateTime +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
