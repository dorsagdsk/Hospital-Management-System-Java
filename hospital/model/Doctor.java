package com.hospital.model;

import com.hospital.repository.Identifiable;

import java.util.Objects;

public class Doctor implements Identifiable {
    private String name;
    private  int id;
    private Specialization specialization;

    public Doctor(String name, int id, Specialization specialization) {
        this.name = name;
        this.id = id;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", specialization=" + specialization +
                '}';
    }

}
