# Hospital Management System

A Java-based Hospital Management System designed to manage patients, doctors, and medical appointments.

## Features

- **Patient Management:** Manage patient information and records.
- **Doctor Management:** Manage doctor profiles and specializations.
- **Appointment Management:** Create and manage appointments between patients and doctors.
- **Appointment Status:** Track appointment statuses.
- **Priority Management:** Support appointment priorities.
- **Exception Handling:** Custom exceptions for handling duplicate records, missing doctors or patients, and appointment conflicts.

## Project Structure

The project follows a layered architecture to separate responsibilities:

- `model`: Contains core entities such as Patient, Doctor, and Appointment.
- `repository`: Provides data storage and repository operations using an in-memory implementation.
- `service`: Handles business logic for patients, doctors, and appointments.
- `exception`: Contains custom exception classes.
- `Main.java`: Application entry point.

## Technologies

- Java
- Object-Oriented Programming (OOP)
- Collections Framework
- Exception Handling
- Layered Architecture

## Purpose

This project was developed to practice Java programming, object-oriented design, separation of concerns, and implementing a structured management system.
