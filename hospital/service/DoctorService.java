package com.hospital.service;

import com.hospital.exception.DuplicateDoctorException;
import com.hospital.exception.DoctorNotFoundException;
import com.hospital.model.Doctor;

import com.hospital.repository.Repository;

import java.util.List;

public class DoctorService {

    private final Repository<Doctor> doctorRepository;

    public DoctorService(Repository<Doctor> doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void addDoctor(Doctor doctor) {

        if (doctorRepository.existsById(doctor.getId())) {
            throw new DuplicateDoctorException(
                    "Doctor with id " + doctor.getId() + " already exists"
            );
        }

        doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(int id) {

        return doctorRepository.findById(id)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor with id " + id + " not found"
                        ));
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public void updateDoctor(Doctor doctor) {

        if (!doctorRepository.existsById(doctor.getId())) {
            throw new DoctorNotFoundException(
                    "Doctor with id " + doctor.getId() + " not found"
            );
        }

        doctorRepository.update(doctor);
    }

    public void deleteDoctor(int id) {

        if (!doctorRepository.existsById(id)) {
            throw new DoctorNotFoundException(
                    "Doctor with id " + id + " not found"
            );
        }

        doctorRepository.deleteById(id);
    }
}