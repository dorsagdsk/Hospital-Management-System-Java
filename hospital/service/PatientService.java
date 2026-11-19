package com.hospital.service;

import com.hospital.exception.DuplicatePatientException;
import com.hospital.exception.PatientNotFoundException;
import com.hospital.model.Patient;
import com.hospital.repository.Repository;


import java.util.List;


public class PatientService {
    private final Repository<Patient> patientRepository;

    public PatientService(Repository<Patient> patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void save(Patient patient) throws DuplicatePatientException{

            if (!patientRepository.existsById(patient.getId())) {
                patientRepository.save(patient);
                System.out.println("finish");
            }else {
                throw new DuplicatePatientException("its here");
            }
    }

    public Patient getPatientById(int id) {
        return patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundException(
                                "Patient with id " + id + " not found"
                        ));
    }


    public List<Patient> getAllPatients(){

        return patientRepository.findAll();
    }
    
    public void updatePatient(Patient patient)throws PatientNotFoundException{
        if (patientRepository.existsById(patient.getId())){
            patientRepository.update(patient);
        }else {
            throw new PatientNotFoundException("not found");
        }
    }
    public void deletePatient(int id)throws PatientNotFoundException{
        if (patientRepository.existsById(id)){
            patientRepository.deleteById(id);
        }else {
            throw new PatientNotFoundException("not found");
        }
    }
}
