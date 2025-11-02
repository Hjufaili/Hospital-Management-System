package main;

import Entity.*;
import Service.*;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;


public class HospitalManagementApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static boolean running = true;

    private static final DoctorService doctorService = new DoctorService();
    private static final NurseService nurseService = new NurseService();
    private static final DepartmentService departmentService = new DepartmentService();
    private static final AppointmentService appointmentService = new AppointmentService();
    private static final MedicalRecordService medicalRecordService = new MedicalRecordService();
    private static final PatientService patientService = new PatientService();
    private static final Patient patient=new Patient();

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("WELCOME TO HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("===============================================");

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
        System.out.println("9. Back");


        System.out.print("Enter choice: ");

        switch (getUserChoice()) {
            case 1 -> registerNewPatient();
            case 2 -> patient.displaySummary();
            case 3 -> {
                System.out.print("Enter patient name or ID: ");
                String keyword = scanner.nextLine();
                patientService.search(keyword);
            }
            case 4 -> {
                System.out.print("Enter patient ID to remove: ");
                String id = scanner.nextLine();
                patientService.remove(id);
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

        PatientService.addPatient(first,last,phone,blood,email);

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
            case 2 -> doctorService.getAll();
            case 3 -> {
                System.out.print("Enter specialization or name: ");
                String keyword = scanner.nextLine();
                doctorService.search(keyword);
            }
            case 4 -> {
                System.out.print("Enter doctor ID to remove: ");
                String id = scanner.nextLine();
                doctorService.remove(id);
            }
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
        int years = Integer.parseInt(scanner.nextLine());
        scanner.nextLine();

        Doctor d = new Doctor(first, last, spec, qual, years, "DPT01");
        doctorService.add(d);
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
