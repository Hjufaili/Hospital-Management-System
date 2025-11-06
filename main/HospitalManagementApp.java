package main;

import Entity.*;
import Service.*;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class HospitalManagementApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static boolean running = true;

    private static final DoctorService doctorService = new DoctorService();
    private static final NurseService nurseService = new NurseService();
    private static final AppointmentService appointmentService = new AppointmentService();
    private static final MedicalRecordService medicalRecordService = new MedicalRecordService();
    private static final PatientService patientService = new PatientService();
    private static final DepartmentService departmentService = new DepartmentService(doctorService);

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("WELCOME TO HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("===============================================");

        addSampleData();

        while (running) {
            showMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1 -> patientManagementMenu();
                case 2 -> doctorManagementMenu();
                case 3 -> nurseManagementMenu();
                case 4 -> appointmentManagementMenu();
                case 5 -> medicalRecordManagementMenu();
                case 6 -> departmentManagementMenu();
                case 7 -> reportsAndStatisticsMenu();
                case 8 -> exitApp();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private static void showMainMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Patient Management");
        System.out.println("2. Doctor Management");
        System.out.println("3. Nurse Management");
        System.out.println("4. Appointment Management");
        System.out.println("5. Medical Records Management");
        System.out.println("6. Department Management");
        System.out.println("7. Reports and Statistics");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }


    private static void patientManagementMenu() {
        System.out.println("\n--- Patient Management ---");
        System.out.println("1. Register New Patient");
        System.out.println("2. Register New InPatient");
        System.out.println("3. Register New OutPatient");
        System.out.println("4. Register New  Emergency Patient ");
        System.out.println("5. View All Patients");
        System.out.println("6. Search Patient");
        System.out.println("7. Update Patient Information");
        System.out.println("8. Remove Patient");
        System.out.println("9. View Patient Medical History");
        System.out.println("10. Back");


        System.out.print("Enter choice: ");

        switch (getUserChoice()) {
            case 1 -> registerNewPatient();
            case 2 -> registerNewInPatient();
            case 3 -> registerNewOutPatient();
            case 4 -> registerNewEmergencyPatient();
            case 5 -> patientService.getAll();
            case 6 -> {
                System.out.print("Enter patient name or ID to search: ");
                String keyword = scanner.nextLine();
                patientService.search(keyword);
            }
            case 7 -> updatePatientInfo();
            case 8 -> {
                System.out.print("Enter patient ID to remove: ");
                String id = scanner.nextLine();
                patientService.remove(id);
            }
            case 9 -> viewPatientMedicalHistory();
            case 10 -> {
            }
            default -> System.out.println("Invalid option.");
        }
    }

    private static void registerNewPatient() {
        System.out.print("Enter first name: ");
        String first = scanner.nextLine();
        System.out.print("Enter last name: ");
        String last = scanner.nextLine();
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter bloodGroup: ");
        String blood = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Patient p = new Patient(first, last, gender, phone, blood, email);
        patientService.add(p);
        System.out.println("Patient registered successfully: " + p.getPatientId());
    }

    private static void registerNewInPatient() {
        System.out.print("Enter first name: ");
        String first = scanner.nextLine();
        System.out.print("Enter last name: ");
        String last = scanner.nextLine();
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter bloodGroup: ");
        String blood = scanner.nextLine();
        System.out.print("Enter Admitting Doctor ID: ");
        String docId = scanner.nextLine();
        System.out.print("Enter Room Number: ");
        String room = scanner.nextLine();
        System.out.print("Enter Daily Charges: ");
        Double charges = scanner.nextDouble();

        InPatient p = new InPatient(first, last, gender, phone, blood, docId, room, charges);
        patientService.add(p);
        System.out.println("InPatient registered successfully: " + p.getPatientId());
    }

    private static void registerNewOutPatient() {
        System.out.print("Enter first name: ");
        String first = scanner.nextLine();
        System.out.print("Enter last name: ");
        String last = scanner.nextLine();
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter bloodGroup: ");
        String blood = scanner.nextLine();
        System.out.print("Enter Preferred Doctor ID: ");
        String docId = scanner.nextLine();

        OutPatient p = new OutPatient(first, last, gender, phone, blood, docId);
        patientService.add(p);
        System.out.println("OutPatient registered successfully: " + p.getPatientId());
    }

    private static void registerNewEmergencyPatient() {
        System.out.print("Enter first name: ");
        String first = scanner.nextLine();
        System.out.print("Enter last name: ");
        String last = scanner.nextLine();
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter bloodGroup: ");
        String blood = scanner.nextLine();
        System.out.print("Enter Emergency Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter Arrival Mode (e.g., Ambulance, Walk-in): ");
        String arrival = scanner.nextLine();
        System.out.print("Enter Triage Level (1-5): ");
        Integer triage = scanner.nextInt();
        scanner.nextLine();

        EmergencyPatient p = new EmergencyPatient(first, last, gender, phone, blood, type, arrival, triage);
        patientService.add(p);
        System.out.println("Emergency Patient registered successfully: " + p.getPatientId());
    }

    private static void updatePatientInfo() {
        System.out.print("Enter patient ID to update: ");
        String id = scanner.nextLine();
        Patient p = patientService.getPatientById(id);

        if (p != null) {
            System.out.println("Updating patient: " + p.getFirstName() + " " + p.getLastName());
            System.out.print("Enter new phone number (current: " + p.getPhoneNumber() + "): ");
            String newPhone = scanner.nextLine();
            if (!newPhone.isBlank()) p.setPhoneNumber(newPhone);

            System.out.print("Enter new email (current: " + p.getEmail() + "): ");
            String newEmail = scanner.nextLine();
            if (!newEmail.isBlank()) p.setEmail(newEmail);

            patientService.update(p);
            System.out.println("Patient information updated.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void viewPatientMedicalHistory() {
        System.out.print("Enter patient ID to view medical history: ");
        String id = scanner.nextLine();
        Patient patient = patientService.getPatientById(id);

        if (patient != null) {
            System.out.println("\n--- MEDICAL HISTORY FOR " + patient.getFirstName().toUpperCase() + " " + patient.getLastName().toUpperCase() + " ---");

            // This relies on MedicalRecordService having a search method,
            // or the Patient object already containing the list of records.

            List<MedicalRecord> records = patient.getMedicalRecords();

            if (records == null || records.isEmpty()) {
                System.out.println("No medical records found for this patient.");
                return;
            }

            for (MedicalRecord record : records) {
                System.out.println("----------------------------------------");
                record.displayInfo(); // Assuming MedicalRecord has a displayInfo method
            }
            System.out.println("----------------------------------------");

        } else {
            System.out.println("Patient with ID " + id + " not found.");
        }
    }


    private static void doctorManagementMenu() {
        System.out.println("\n--- Doctor Management ---");
        System.out.println("1. Add Doctor");
        System.out.println("2. Add Surgeon");
        System.out.println("3. Add Consultant");
        System.out.println("4. Add  General Practitioner");
        System.out.println("5. View All Doctors");
        System.out.println("6. Search Doctor by Specialization");
        System.out.println("7. View Available Doctors");
        System.out.println("8. Assign Patient to Doctor");
        System.out.println("9. Update Doctor Information");
        System.out.println("10. Remove Doctor");
        System.out.println("11. Back");

        System.out.print("Enter choice: ");

        switch (getUserChoice()) {
            case 1 -> addDoctor();
            case 2 -> addSurgeon();
            case 3 -> addConsultant();
            case 4 -> addGeneralPractitioner();
            case 5 -> doctorService.getAll();
            case 6 -> {
                System.out.print("Enter specialization or name to search: ");
                String keyword = scanner.nextLine();
                doctorService.search(keyword);
            }
            case 7 -> doctorService.getAvailableDoctors(); // Assuming this method exists
            case 8 -> assignPatientToDoctor(); // New method to implement
            case 9 -> updateDoctorInfo(); // New method to implement
            case 10 -> {
                System.out.print("Enter doctor ID to remove: ");
                String id = scanner.nextLine();
                doctorService.remove(id);
            }
            case 11 -> {} // Back
            default -> System.out.println("Invalid option.");
        }
    }

    private static void addDoctor() {
        System.out.print("Enter first name: ");
        String first = scanner.nextLine();
        System.out.print("Enter last name: ");
        String last = scanner.nextLine();
        System.out.print("Enter specialization: ");
        String spec = scanner.nextLine();
        System.out.print("Enter qualification: ");
        String qual = scanner.nextLine();
        System.out.print("Enter years of experience: ");
        int years = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter department ID: ");
        String deptId = scanner.nextLine();

        Doctor d = new Doctor(first, last, spec, qual, years, deptId);
        doctorService.add(d);
        System.out.println("Doctor registered successfully: " + d.getDoctorId());
    }

    private static void addSurgeon() {
        System.out.print("Enter first name: ");
        String first = scanner.nextLine();
        System.out.print("Enter last name: ");
        String last = scanner.nextLine();
        System.out.print("Enter specialization: ");
        String spec = scanner.nextLine();
        System.out.print("Enter department ID: ");
        String deptId = scanner.nextLine();

        Surgeon s = new Surgeon(first, last, spec, deptId);
        doctorService.add(s);
        System.out.println("Surgeon registered successfully: " + s.getDoctorId());
    }

    private static void addConsultant() {
        System.out.print("Enter first name: ");
        String first = scanner.nextLine();
        System.out.print("Enter last name: ");
        String last = scanner.nextLine();
        System.out.print("Enter specialization: ");
        String spec = scanner.nextLine();
        System.out.print("Enter department ID: ");
        String deptId = scanner.nextLine();

        Consultant c = new Consultant(first, last, spec, deptId);
        doctorService.add(c);
        System.out.println("Consultant registered successfully: " + c.getDoctorId());
    }

    private static void addGeneralPractitioner() {
        System.out.print("Enter first name: ");
        String first = scanner.nextLine();
        System.out.print("Enter last name: ");
        String last = scanner.nextLine();
        System.out.print("Enter specialization: ");
        String spec = scanner.nextLine();
        System.out.print("Enter department ID: ");
        String deptId = scanner.nextLine();

        GeneralPractitioner gp = new GeneralPractitioner(first, last, spec, deptId);
        doctorService.add(gp);
        System.out.println("General Practitioner registered successfully: " + gp.getDoctorId());
    }

    private static void assignPatientToDoctor() {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();

        Patient p = patientService.getPatientById(patientId);
        Doctor d = doctorService.getDoctorById(doctorId);

        if (p != null && d != null) {
            d.getAssignedPatients().add(p);
            System.out.println("Patient " + p.getFirstName() + " assigned to Dr. " + d.getLastName());
        } else {
            System.out.println("Invalid Patient ID or Doctor ID.");
        }
    }

    private static void updateDoctorInfo() {
        System.out.print("Enter doctor ID to update: ");
        String id = scanner.nextLine();
        Doctor d = doctorService.getDoctorById(id);

        if (d != null) {
            System.out.println("Updating doctor: Dr. " + d.getLastName());
            System.out.print("Enter new consultation fee (current: " + d.getConsultationFee() + "): ");
            Double newFee = scanner.nextDouble();
            if (newFee != null) d.setConsultationFee(newFee);

            doctorService.update(d);
            System.out.println("Doctor information updated.");
        } else {
            System.out.println("Doctor not found.");
        }
    }


    private static void nurseManagementMenu() {
        System.out.println("\n--- Nurse Management ---");
        System.out.println("1. Add Nurse");
        System.out.println("2. View All Nurses");
        System.out.println("3. View Nurses by Department");
        System.out.println("4. View Nurses by Shift");
        System.out.println("5. Assign Nurse to Patient");
        System.out.println("6. Update Nurse Information");
        System.out.println("7. Remove Nurse");
        System.out.println("8. Back");

        System.out.print("Enter choice: ");

        switch (getUserChoice()) {
            case 1 -> addNurse();
            case 2 -> nurseService.getAll();
            default -> System.out.println("Invalid option.");
        }
    }

    private static void addNurse() {
        System.out.print("Enter nurse name: ");
        String name = scanner.nextLine();
        System.out.print("Enter department ID: ");
        String dept = scanner.nextLine();
        System.out.print("Enter shift (Morning/Evening): ");
        String shift = scanner.nextLine();

        Nurse n = new Nurse();
        n.setNurseId(HelperUtils.generateId("NUR"));
        n.setFirstName(name);
        n.setDepartmentId(dept);
        n.setShift(shift);

        nurseService.add(n);
    }


    private static void appointmentManagementMenu() {
        System.out.println("\n--- Appointment Management ---");
        System.out.println("1. Schedule New Appointment");
        System.out.println("2. View All Appointments");
        System.out.println("3. View Appointments by Patient");
        System.out.println("4. View Appointments by Doctor");
        System.out.println("5. View Appointments by Date");
        System.out.println("6. Reschedule Appointment");
        System.out.println("7. Cancel Appointment");
        System.out.println("8. Complete Appointment");
        System.out.println("9. View Upcoming Appointments");
        System.out.println("10. Back");


        System.out.print("Enter choice: ");

        switch (getUserChoice()) {
            case 1 -> scheduleAppointment();
            case 2 -> appointmentService.getAll();
            default -> System.out.println("Invalid option.");
        }
    }

    private static void scheduleAppointment() {
        System.out.print("Enter patient ID: ");
        String pid = scanner.nextLine();
        System.out.print("Enter doctor ID: ");
        String did = scanner.nextLine();
        System.out.print("Enter appointment date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();

        Appointment ap = new Appointment();
        ap.setAppointmentId(HelperUtils.generateId("APT"));
        ap.setPatientId(pid);
        ap.setDoctorId(did);
        ap.setAppointmentDate(LocalDate.parse(dateStr));
        ap.setStatus("Scheduled");

        appointmentService.add(ap);
    }


    private static void medicalRecordManagementMenu() {
        System.out.println("\n--- Medical Records ---");
        System.out.println("1. Create Record");
        System.out.println("2. View All Records");
        System.out.print("Enter choice: ");

        switch (getUserChoice()) {
            case 1 -> createMedicalRecord();
            case 2 -> medicalRecordService.getAll();
            default -> System.out.println("Invalid option.");
        }
    }

    private static void createMedicalRecord() {
        System.out.print("Enter patient ID: ");
        String pid = scanner.nextLine();
        System.out.print("Enter doctor ID: ");
        String did = scanner.nextLine();
        System.out.print("Enter diagnosis: ");
        String diag = scanner.nextLine();

        MedicalRecord record = new MedicalRecord();
        record.setRecordId(HelperUtils.generateId("REC"));
        record.setPatientId(pid);
        record.setDoctorId(did);
        record.setDiagnosis(diag);
        record.setVisitDate(new Date());

        medicalRecordService.add(record);
    }


    private static void departmentManagementMenu() {
        System.out.println("\n--- Department Management ---");
        System.out.println("1. Add Department");
        System.out.println("2. View All Departments");
        System.out.print("Enter choice: ");

        switch (getUserChoice()) {
            case 1 -> addDepartment();
            case 2 -> departmentService.getAll();
            default -> System.out.println("Invalid option.");
        }
    }

    private static void addDepartment() {
        System.out.print("Enter department name: ");
        String name = scanner.nextLine();

        Department d = new Department();
        d.setDepartmentId(HelperUtils.generateId("DEPT"));
        d.setDepartmentName(name);

        departmentService.add(d);
    }


    private static void reportsAndStatisticsMenu() {
        System.out.println("\n--- Reports ---");
        System.out.println("1. Daily Appointments Report");
        System.out.println("2. Doctor Performance Report");
        System.out.print("Enter choice: ");

        switch (getUserChoice()) {
            case 1 -> System.out.println("(Demo) Total appointments today: 12");
            case 2 -> System.out.println("(Demo) Top-performing doctor: Dr. Ahmed");
            default -> System.out.println("Invalid option.");
        }
    }


    private static void exitApp() {
        System.out.println("\nExiting Hospital Management System...");
        running = false;
    }
}
