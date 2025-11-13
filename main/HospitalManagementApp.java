package main;

import Entity.*;
import Service.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
                ;
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


        Patient p = patientService.createPatient(first, last, phone, blood, email);
        if (p != null) {
            System.out.println("Patient registered successfully: " + p.getPatientId());
        }
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

        InPatient p = InPatient.builder()
                .firstName(first).lastName(last).gender(gender).phoneNumber(phone).bloodGroup(blood)
                .admittingDoctorId(docId).roomNumber(room).dailyCharges(charges)
                .build();

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

        OutPatient p = OutPatient.builder()
                .firstName(first).lastName(last).gender(gender).phoneNumber(phone)
                .bloodGroup(blood).preferredDoctorId(docId).build();

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

        EmergencyPatient p = EmergencyPatient.builder()
                .firstName(first).lastName(last).gender(gender)
                .phoneNumber(phone).bloodGroup(blood).emergencyType(type)
                .arrivalMode(arrival).triageLevel(triage).build();

        patientService.add(p);
        System.out.println("Emergency Patient registered successfully: " + p.getPatientId());
    }

    private static void updatePatientInfo() {
        System.out.print("Enter patient ID to update: ");
        String id = scanner.nextLine();
        Patient p = patientService.getPatientById(id);

        if (p != null) {
            System.out.println("Updating patient: " + p.getFirstName() + " " + p.getLastName());
            System.out.println("press enter to keep it");

            System.out.print("Enter new phone number (current: " + p.getPhoneNumber() + "): ");
            String newPhone = scanner.nextLine();
            if (!newPhone.isBlank()) p.setPhoneNumber(newPhone);
            System.out.print("Enter new email (current: " + p.getEmail() + "): ");
            System.out.println("press enter to keep it");
            String newEmail = scanner.nextLine();
            if (!newEmail.isBlank()) p.setEmail(newEmail);

            patientService.editPatient(id, p);
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

            medicalRecordService.displayPatientHistory(id);

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
            case 7 -> doctorService.getAvailableDoctors();
            case 8 -> assignPatientToDoctor();
            case 9 -> updateDoctorInfo();
            case 10 -> {
                System.out.print("Enter doctor ID to remove: ");
                String id = scanner.nextLine();
                doctorService.remove(id);
            }
            case 11 -> {
            } // Back
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


        Doctor d = Doctor.builder().firstName(first).lastName(last).specialization(spec)
                .qualification(qual).experienceYears(years).departmentId(deptId).build();

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


        Surgeon s = Surgeon.builder().firstName(first).lastName(last)
                .specialization(spec).departmentId(deptId).build();

        doctorService.add(s);
        System.out.println("Surgeon registered successfully: ");
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

        Consultant c = Consultant.builder().firstName(first).lastName(last).specialization(spec)
                .departmentId(deptId).build();

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

        GeneralPractitioner gp = GeneralPractitioner.builder().firstName(first).lastName(last)
                .specialization(spec).departmentId(deptId).build();
        doctorService.add(gp);
        System.out.println("General Practitioner registered successfully: " + gp.getDoctorId());
    }

    private static void assignPatientToDoctor() {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();

        doctorService.assignPatient(doctorId, patientId);
    }

    private static void updateDoctorInfo() {
        System.out.print("Enter doctor ID to update: ");
        String id = scanner.nextLine();
        Doctor d = doctorService.getDoctorById(id);

        if (d != null) {
            System.out.println("Updating doctor: Dr. " + d.getLastName());
            System.out.print("Enter new consultation fee (current: " + d.getConsultationFee() + "): ");
            Double newFee = scanner.nextDouble();
            d.setConsultationFee(newFee);

            doctorService.editDoctor(id, d);
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
            case 3 -> {
                System.out.print("Enter nurse ID: ");
                String id = scanner.nextLine();
                nurseService.getNurseById(id);
            }
            case 4 -> {
                System.out.print("Enter nurse shift: ");
                String shift = scanner.nextLine();
                nurseService.getNursesByShift(shift);
            }
            case 5 -> assignNurseToPatient();
            case 6 -> updateNurseInfo();
            case 7 -> {
                System.out.print("Enter nurse ID to remove: ");
                String id = scanner.nextLine();
                nurseService.remove(id);
            }
            default -> System.out.println("Invalid option.");
        }
    }

    private static void addNurse() {
        System.out.print("Enter nurse name: ");
        String name = scanner.nextLine();
        System.out.print("Enter department ID: ");
        String dept = scanner.nextLine();
        System.out.print("Enter shift (Morning/Evening/Night): ");
        String shift = scanner.nextLine();

        Nurse n = Nurse.builder().firstName(name).departmentId(dept)
                .shift(shift).build();

        nurseService.add(n);
        System.out.println("Nurse added successfully");
    }


    public static void assignNurseToPatient() {
        System.out.print("Enter Nurse ID: ");
        String nurseId = scanner.nextLine();
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();


        Patient p = patientService.getPatientById(patientId);
        Nurse n = nurseService.getNurseById(nurseId);

        if (p != null && n != null) {
            n.assignPatient(p);
            System.out.println("Nurse " + n.getFirstName() + " assigned to Patient. " + p.getLastName());
        } else {
            System.out.println("Invalid Nurse ID or Patient ID.");
        }
    }

    public static void updateNurseInfo() {
        System.out.print("Enter nurse ID to update: ");
        String id = scanner.nextLine();
        Nurse d = nurseService.getNurseById(id);

        if (d != null) {
            System.out.println("Updating nurse: Dr. " + d.getLastName());
            System.out.print("Enter new shift (current: " + d.getShift() + "): ");
            String newShift = scanner.nextLine();
            if (!newShift.isBlank()) d.setShift(newShift);

            System.out.print("Enter new department ID (current: " + d.getDepartmentId() + "): ");
            String newDeptId = scanner.nextLine();
            if (!newDeptId.isBlank()) d.setDepartmentId(newDeptId);


            nurseService.editNurse(id, d);
            System.out.println("Nurse information updated.");
        } else {
            System.out.println("Nurse not found.");
        }
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
            case 3 -> {
                System.out.print("Enter patient ID: ");
                String pid = scanner.nextLine();
                appointmentService.getAppointmentsByPatient(pid);
            }
            case 4 -> {
                System.out.print("Enter doctor ID: ");
                String did = scanner.nextLine();
                appointmentService.getAppointmentsByDoctor(did);
            }
            case 5 -> {
                System.out.print("Enter a date (yyyy-MM-dd): ");
                String input = scanner.nextLine();

                // Parse the input string into a LocalDate
                LocalDate dateStr = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                appointmentService.getAppointmentsByDate(dateStr);
            }
            case 6 -> rescheduleAppointment();
            case 7 -> {
                System.out.print("Enter Appointment ID for canceling: ");
                String aid = scanner.nextLine();
                appointmentService.cancelAppointment(aid);
            }
            case 8 -> {
                System.out.print("Enter Appointment ID which completing: ");
                String aid = scanner.nextLine();
                appointmentService.completeAppointment(aid);
            }
            case 9 -> appointmentService.getUpcomingAppointments();
            case 10 -> {
            }
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
        System.out.print("Enter appointment time (HH:mm): ");
        String timeStr = scanner.nextLine();

        Appointment ap = Appointment.builder()
                .patientId(pid)
                .doctorId(did)
                .appointmentDate(LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .appointmentTime(LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm")))
                .build();

        appointmentService.add(ap);
    }

    private static void rescheduleAppointment() {
        System.out.print("Enter appointment ID: ");
        String aid = scanner.nextLine();

        System.out.print("Enter a new Date (yyyy-MM-dd): ");
        String inputDate = scanner.nextLine();
        // Parse the input string into a LocalDate
        LocalDate newDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.print("Enter new Time: ");
        String inputTime = scanner.nextLine();
        LocalTime newTime = LocalTime.parse(inputTime, DateTimeFormatter.ofPattern("HH:mm"));

        appointmentService.rescheduleAppointment(aid, newDate, newTime);
    }


    private static void medicalRecordManagementMenu() {
        System.out.println("\n--- Medical Records ---");
        System.out.println("1. Create Medical Record");
        System.out.println("2. View All Records");
        System.out.println("3. View Records by Patient");
        System.out.println("4. View Records by Doctor");
        System.out.println("5. Update Medical Record");
        System.out.println("6. Delete Medical Record");
        System.out.println("7. Generate Patient History Report");
        System.out.println("8. Back");

        System.out.print("Enter choice: ");

        switch (getUserChoice()) {
            case 1 -> createMedicalRecord();
            case 2 -> medicalRecordService.getAll();
            case 3 -> {
                System.out.print("Enter patient ID: ");
                String pid = scanner.nextLine();
                medicalRecordService.getRecordsByPatientId(pid);
            }
            case 4 -> {
                System.out.print("Enter doctor ID: ");
                String did = scanner.nextLine();
                medicalRecordService.getRecordsByDoctorId(did);
            }
            case 5 -> updateRecordInfo();
            case 6 -> {
                System.out.println("Enter Medical record ID: ");
                String recordId = scanner.nextLine();
                medicalRecordService.remove(recordId);
            }
            case 7 -> {
                System.out.print("Enter patient ID: ");
                String pid = scanner.nextLine();
                medicalRecordService.displayPatientHistory(pid);
            }
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


        medicalRecordService.createMedicalRecord(pid,did,diag);
        System.out.println("Medical record added successfully");
    }

    private static void updateRecordInfo() {
        System.out.print("Enter Medical record ID to update: ");
        String id = scanner.nextLine();
        MedicalRecord d = medicalRecordService.getMedicalRecordById(id);

        if (d != null) {
            System.out.println("Updating medical record:");
            System.out.print("Enter new diagnosis (current: " + d.getDiagnosis() + "): ");
            String newDig = scanner.nextLine();
            System.out.print("Enter new prescription (current: " + d.getPrescription() + "): ");
            String newPres = scanner.nextLine();
            System.out.print("Enter new notes (current: " + d.getNotes() + "): ");
            String newNotes = scanner.nextLine();
            if (newDig != null) d.setDiagnosis(newDig);
            if (newPres != null) d.setPrescription(newPres);
            if (newNotes != null) d.setNotes(newNotes);


            medicalRecordService.editMedicalRecord(id, d);
            System.out.println("Medical record information updated.");
        } else {
            System.out.println("Medical record not found.");
        }
    }


    private static void departmentManagementMenu() {
        System.out.println("\n--- Department Management ---");
        System.out.println("1. Add Department");
        System.out.println("2. View All Departments");
        System.out.println("3. View Department Details");
        System.out.println("4. Assign Doctor to Department");
        System.out.println("5. Assign Nurse to Department");
        System.out.println("6. Update Department Information");
        System.out.println("7. View Department Statistics");
        System.out.println("8. Back");

        System.out.print("Enter choice: ");

        switch (getUserChoice()) {
            case 1 -> addDepartment();
            case 2 -> departmentService.getAll();
            case 3 -> {

            }
            case 4 -> assignDoctorToDepartment();
            case 5 -> assignNurseToDepartment();
            case 6 -> updateDepartmentInfo();
           case 7 -> viewDepartmentStatistics();
            case 8 -> {
            }
            default -> System.out.println("Invalid option.");
        }
    }

    private static void addDepartment() {
        System.out.print("Enter department name: ");
        String departmentName = scanner.nextLine();
        System.out.print("Enter contact phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter contact phone: ");
        String headDoctorId = scanner.nextLine();
        System.out.print("Enter contact phone: ");
        int bedCapacity = scanner.nextInt();
        scanner.nextLine();

        departmentService.createDepartment(departmentName,phone,headDoctorId,bedCapacity);
        System.out.println("Department added successfully");

    }

    private static void assignDoctorToDepartment() {
        System.out.print("Enter doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Enter department ID: ");
        String depId = scanner.nextLine();


        Doctor d = doctorService.getDoctorById(doctorId);
        Department p=departmentService.getDepartmentById(depId);

        if (p != null && d != null) {
            p.assignDoctor(d);
            System.out.println("Doctor " + d.getFirstName() + " assigned to department. " + p.getDepartmentName());
        } else {
            System.out.println("Invalid department ID or doctor ID.");
        }
    }

    private static void assignNurseToDepartment() {
        System.out.print("Enter nurse ID: ");
        String nurseId = scanner.nextLine();
        System.out.print("Enter department ID: ");
        String depId = scanner.nextLine();


        Nurse n = nurseService.getNurseById(nurseId);
        Department p=departmentService.getDepartmentById(depId);

        if (p != null && n != null) {
            p.assignNurse(n);
            System.out.println("Nurse " + n.getFirstName() + " assigned to department. " + p.getDepartmentName());
        } else {
            System.out.println("Invalid department ID or doctor ID.");
        }
    }

    public static void updateDepartmentInfo() {
        System.out.println("\n--- Update Department Data ---");
        System.out.print("Enter Department ID to update: ");
        String departmentId = scanner.nextLine();

        Department existingDept = departmentService.getDepartmentById(departmentId);

        if (existingDept == null) {
            System.out.println("Cannot update. Department with ID " + departmentId + " not found.");
            return;
        }

        System.out.println("Found Department: " + existingDept.getDepartmentName());
        System.out.println("Enter new details (leave blank to keep current value):");

        System.out.print("New Department Name [" + existingDept.getDepartmentName() + "]: ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            existingDept.setDepartmentName(newName);
        }

        System.out.print("New Head Doctor ID [" + existingDept.getHeadDoctorId() + "]: ");
        String newHeadDoctorId = scanner.nextLine();
        if (!newHeadDoctorId.isEmpty()) {
            existingDept.setHeadDoctorId(newHeadDoctorId);
        }

        System.out.print("New Bed Capacity [" + existingDept.getBedCapacity() + "]: ");
        int newCapacityStr = scanner.nextInt();
        scanner.nextLine();


        Department updatedDept = Department.builder().departmentName(newName)
                .headDoctorId(newHeadDoctorId).bedCapacity(newCapacityStr)
                        .build();

        departmentService.editDepartment(departmentId, updatedDept);
        System.out.println("Department update process complete.");
    }


    private static void reportsAndStatisticsMenu() {
        System.out.println("\n--- Reports ---");
        System.out.println("1. Daily Appointments Report");
        System.out.println("2. Doctor Performance Report");
        System.out.println("3 Department Occupancy Report ");
        System.out.println("4 Patient Statistics ");
        System.out.println("5 Emergency Cases Report");
        System.out.println("6. Back");

        System.out.print("Enter choice: ");

        switch (getUserChoice()) {
            case 1 -> {

            }
            case 2 -> {
            }
            case 3 -> {
            }
            case 4 -> {
            }
            case 5 -> {
            }
            case 6 -> {
            }
            default -> System.out.println("Invalid option.");
        }
    }


    private static void exitApp() {
        System.out.println("\nExiting Hospital Management System...");
        running = false;
    }
}
