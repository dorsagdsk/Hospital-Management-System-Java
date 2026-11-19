import com.hospital.exception.*;
import com.hospital.model.*;
import com.hospital.repository.InMemoryRepository;
import com.hospital.repository.Repository;
import com.hospital.service.AppointmentService;
import com.hospital.service.DoctorService;
import com.hospital.service.PatientService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.*;
import java.util.regex.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static Repository<Patient> patientRepository = new InMemoryRepository<>();
    static Repository<Doctor> doctorRepository = new InMemoryRepository<>();
    static Repository<Appointment> appointmentRepository = new InMemoryRepository<>();

    static PatientService patientService = new PatientService(patientRepository);
    static DoctorService doctorService = new DoctorService(doctorRepository);

    static AppointmentService appointmentService =
            new AppointmentService(
                    appointmentRepository,
                    patientRepository,
                    doctorRepository
            );

    public static void main(String[] args) {


        boolean running = true;

        while (running) {

            System.out.println("""
                    
                    ===== Hospital Management System =====
                    
                    1. Add Patient
                    2. Find Patient
                    3. Show All Patients
                    4. Update Patient
                    5. Delete Patient
                    
                    6. Add Doctor
                    7. Find Doctor
                    8. Show All Doctors
                    9. Update Doctor
                    10. Delete Doctor
                    
                    11. Create Appointment
                    12. Show Doctor Appointments
                    13. Show Patient Appointments
                    14. Show Next Appointment
                    
                    0. Exit
                    """);

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    Patient patient = createPatient();
                    try {
                        patientService.save(patient);
                        System.out.println("Patient added successfully.");
                    } catch (DuplicatePatientException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    findPById();

                    break;

                case 3:
                    showAllPatients();
                    break;

                case 4:
                    updatePatient();
                    break;

                case 5:
                    deletePatient();
                    break;

                case 6:

                    Doctor doctor = addDoctor();

                    try {
                        doctorService.addDoctor(doctor);
                        System.out.println("Doctor added successfully.");
                    } catch (DuplicateDoctorException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    findDoctorById();
                    break;

                case 8:
                    showAllDoctors();
                    break;

                case 9:
                    updateDoctor();
                    break;

                case 10:
                    deleteDoctor();
                    break;

                case 11:
                    createAppointment();
                    break;

                case 12:
                    showDoctorAppointments();
                    break;

                case 13:
                    showPatientAppointments();
                    break;

                case 14:
                    showNextAppointment();
                    break;

                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        scanner.close();


    }

    public static Patient createPatient() {

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        scanner.nextLine();


        System.out.print("Enter priority: ");
        Priority priority = Priority.valueOf(scanner.nextLine());

        return new Patient(
                name,
                phoneNumber,
                age,
                id,
                priority
        );
    }

    public static void findPById() {
        System.out.println("enter id");
        int a = scanner.nextInt();
        scanner.nextLine();

        try {
            Patient patient1 = patientService.getPatientById(a);
            System.out.println(patient1);
            System.out.println("Patient found successfully.");
        } catch (PatientNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void showAllPatients() {
        List<Patient> patientList = patientService.getAllPatients();
        if (patientList.isEmpty()) {
            System.out.println("dont have patient yet");
            return;
        }

        for (Patient patient : patientList) {
            System.out.println(patient);
        }
    }

    public static void updatePatient() {

        System.out.println("Which patient do you want to update? Enter ID:");
        showAllPatients();

        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new information:");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter priority: ");
        Priority priority = Priority.valueOf(scanner.nextLine());

        Patient patient = new Patient(
                name,
                phoneNumber,
                age,
                id,
                priority
        );

        try {
            patientService.updatePatient(patient);
            System.out.println("Patient updated successfully.");
        } catch (PatientNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void deletePatient() {
        System.out.println("Which patient do you want to delete? Enter ID:");
        showAllPatients();

        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            patientService.deletePatient(id);
            System.out.println("Patient deleted successfully.");
        } catch (PatientNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static Doctor addDoctor() {

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print(
                """
                        Enter Specialization:
                        
                            CARDIOLOGY,
                            NEUROLOGY,
                            ORTHOPEDICS,
                            DERMATOLOGY,
                            PEDIATRICS,
                            OPHTHALMOLOGY,
                            UROLOGY,
                            GENERAL_MEDICINE
                        """);
        Specialization specialization = Specialization.valueOf(scanner.nextLine());

        return new Doctor(name, id, specialization);
    }

    public static void findDoctorById() {
        System.out.println("enter id");
        int a = scanner.nextInt();
        scanner.nextLine();

        try {
            Doctor doctor = doctorService.getDoctorById(a);
            System.out.println(doctor);
            System.out.println("doctor found successfully.");
        } catch (DoctorNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void showAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println(" dont have doctor yet");
            return;
        }
        doctors.forEach(System.out::println);
    }

    public static void updateDoctor() {

        System.out.println("Which doctor do you want to update? Enter ID:");
        showAllDoctors();

        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new information:");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter specialization: ");
        Specialization specialization =
                Specialization.valueOf(scanner.nextLine().toUpperCase());

        Doctor doctor = new Doctor(
                name,
                id,
                specialization
        );

        try {
            doctorService.updateDoctor(doctor);
            System.out.println("Doctor updated successfully.");
        } catch (DoctorNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteDoctor() {

        System.out.println("Which doctor do you want to delete? Enter ID:");
        showAllDoctors();

        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            doctorService.deleteDoctor(id);
            System.out.println("Doctor deleted successfully.");
        } catch (DoctorNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createAppointment() {

        System.out.print("Enter appointment id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        showAllPatients();

        System.out.print("Enter patient id: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        showAllDoctors();

        System.out.print("Enter doctor id: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();

        Patient patient;
        Doctor doctor;

        try {
            patient = patientService.getPatientById(patientId);
            doctor = doctorService.getDoctorById(doctorId);

        } catch (PatientNotFoundException | DoctorNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Enter date and time (yyyy-MM-dd HH:mm): ");
        String dateTimeInput = scanner.nextLine();

        LocalDateTime dateTime;

        try {
            dateTime = LocalDateTime.parse(
                    dateTimeInput,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            );

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return;
        }

        System.out.println("""
                Enter status:
                SCHEDULED
                CANCELLED
                COMPLETED
                """);

        AppointmentStatus status;

        try {
            status = AppointmentStatus.valueOf(
                    scanner.nextLine().toUpperCase()
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid appointment status.");
            return;
        }

        Appointment appointment = new Appointment(
                id,
                patient,
                doctor,
                status,
                dateTime
        );

        try {
            appointmentService.createAppointment(appointment);
            System.out.println("Appointment created successfully.");

        } catch (AppointmentConflictException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showDoctorAppointments() {
        try {
            System.out.println("enter the id of doctor");
            showAllDoctors();
            int doctorId = scanner.nextInt();
            scanner.nextLine();
            List<Appointment> appointmentListDoctor
                    = appointmentService.getAppointmentsByDoctor(doctorId);

            if (appointmentListDoctor.isEmpty()) {
                System.out.println("you dont have appointment yet");
                return;
            }

            appointmentListDoctor.forEach(System.out::println);

        } catch (DoctorNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void showPatientAppointments() {
        try {
            System.out.println("enter the id pf patient");
            showAllPatients();
            int patientId = scanner.nextInt();
            scanner.nextLine();

            List<Appointment> appointmentListPatient
                    = appointmentService.getAppointmentByPatient(patientId);

            if (appointmentListPatient.isEmpty()) {
                System.out.println("you dont get appoentment yet");
                return;
            }

            appointmentListPatient.forEach(System.out::println);

        } catch (PatientNotFoundException e) {
            System.out.println(e.getMessage());

        }

    }

    public static void showNextAppointment() {

        appointmentService.getNextAppointment()
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("No upcoming appointments.")
                );
    }
}