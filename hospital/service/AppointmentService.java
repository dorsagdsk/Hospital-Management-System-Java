package com.hospital.service;

import com.hospital.exception.AppointmentConflictException;
import com.hospital.model.*;
import com.hospital.repository.Repository;
import com.hospital.exception.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public class AppointmentService {
    private final Repository<Appointment> appointmentRepository;
    private final Repository<Patient> patientRepository;
    private final Repository<Doctor> doctorRepository;

    public AppointmentService(
            Repository<Appointment> appointmentRepository,
            Repository<Patient> patientRepository,
            Repository<Doctor> doctorRepository) {

        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

   public void createAppointment(Appointment appointment){

       int patientId = appointment.getPatient().getId();
       int doctorId = appointment.getDoctor().getId();

       if (!patientRepository.existsById(patientId)) {
           throw new PatientNotFoundException("Patient not found");
       }

       if (!doctorRepository.existsById(doctorId)) {
           throw new DoctorNotFoundException("Doctor not found");
       }

       List<Appointment> appointments = appointmentRepository.findAll();


       boolean conflict = appointments.stream()
               .anyMatch(existing ->
                       (
                               existing.getDoctor().getId() == doctorId
                                       ||
                                       existing.getPatient().getId() == patientId
                       )
                               &&
                               existing.getDateTime()
                                       .equals(appointment.getDateTime())
               );

       if (conflict) {
           throw new AppointmentConflictException(
                   "Doctor already has an appointment at this time"
           );
       }

       appointmentRepository.save(appointment);


   }

   public List<Appointment> getAppointmentsByDoctor(int doctorId) {

        return appointmentRepository.findAll()
                .stream()
                .filter(appointment ->
                        appointment.getDoctor().getId() == doctorId)
                .toList();
    }

   public   List<Appointment> getAppointmentByPatient(int patientId){

        return appointmentRepository.findAll().
                stream().filter(appointment ->
                        appointment.getPatient().getId()==patientId)
                .toList();
    }


   public List<Appointment> getAppointmentsByDate(LocalDate date){

        return appointmentRepository.findAll()
                .stream().filter(appointment ->
                        appointment.getDateTime().toLocalDate().equals(date) ).toList();
   }

    public List<Appointment> getAppointmentsByDoctorSorted(int doctorId){
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment ->
                        appointment.getDoctor().getId() == doctorId)
                .sorted(Comparator.comparing(Appointment::getDateTime))
                .toList();

    }

    public Optional<Appointment> getNextAppointment(){
        return appointmentRepository.findAll().stream()
                .filter(appointment->appointment.getDateTime().isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(Appointment::getDateTime))
                        .findFirst();


    }

    public Map<Integer, Long> countAppointmentsByDoctor() {

        return appointmentRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        appointment -> appointment.getDoctor().getId(),
                        Collectors.counting()
                ));
    }
}

