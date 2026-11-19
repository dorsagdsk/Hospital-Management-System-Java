package com.hospital.model;

import com.hospital.repository.Identifiable;

import java.util.Objects;

public class Patient implements Identifiable {

    private String   name;
     private int age;
     private Priority priority;
     private String phoneNumber;
     private  int id;

    public Patient(String name, String phoneNumber, int age, int id,Priority priority) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.id = id;
        this.priority = priority;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber=" + phoneNumber +
                ", id=" + id +
                ", priority" + priority+
                '}';
    }
}
