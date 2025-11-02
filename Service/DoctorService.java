package Service;

import Entity.Doctor;
import Entity.Patient;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;

import java.util.ArrayList;
import java.util.List;

public class DoctorService implements Manageable, Searchable {
    private static List<Doctor> doctors = new ArrayList<>();


    public static void addDoctor(Doctor doctor) {
        if (HelperUtils.isNotNull(doctor)
                && HelperUtils.isValidString(doctor.getSpecialization(), 3)
                && HelperUtils.isPositive(doctor.getExperienceYears())) {
            doctors.add(doctor);
            System.out.println("Doctor added successfully: " + doctor.getFirstName());
        } else {
            System.out.println("Invalid doctor details.");
        }
    }

    public static void editDoctor(String doctorId, Doctor updatedDoctor) {

        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getDoctorId().equals(doctorId)) {
                doctors.add(i, updatedDoctor);
                System.out.println("updated successfully!");
                return;
            }
        }
        System.out.println("Not found doctor:");
    }

    public static void removeDoctor(String doctorId) {
        if (HelperUtils.isNull(doctorId)) {
            System.out.println("Invalid ID.");
            return;
        }
        boolean removed = doctors.removeIf(d -> d.getDoctorId().equalsIgnoreCase(doctorId));
        System.out.println(removed ? "Doctor removed." : "Doctor not found.");
    }

    public static Doctor getDoctorById(String doctorId) {
        for (Doctor d : doctors) {
            if (d.getDoctorId().equals(doctorId)) {
                return d;
            }
        }
        System.out.println("Doctor not found:" + doctorId);
        return null;
    }

    public static void displayAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctor");
            return;
        }
        System.out.println("-----All doctors-----");
        for (Doctor d : doctors) {
            System.out.println(d);
        }
    }

    public static List<Doctor> getDoctorsBySpecialization(String specialization) {
        List<Doctor> results = new ArrayList<>();
        for (Doctor d : doctors) {
            if (d.getSpecialization().equalsIgnoreCase(specialization) && d.getSpecialization() != null) {
                results.add(d);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No doctor in specialization:  " + specialization);
        } else {
            System.out.println("All doctors in specialization" + specialization);
        }

        return results;
    }

    public static List<Doctor> getAvailableDoctors() {
        List<Doctor> available = new ArrayList<>();
        for (Doctor d : doctors) {
            if (d.getAvailableSlots() != null && !d.getAvailableSlots().isEmpty()) {
                available.add(d);
            }
        }

        if (available.isEmpty()) {
            System.out.println("No doctors currently available.");
        } else {
            System.out.println(" available doctors:" + available.size() + " doctors");
        }

        return available;
    }

    public static List<Doctor> getAllDoctor() {
        return doctors;
    }

    public static void addDoctor(String name, String specialization, String phone) {
        Doctor doctor = new Doctor();
        if (name != null && specialization != null && phone != null) {
            doctor.setFirstName(name);
            doctor.setSpecialization(specialization);
            doctor.setPhoneNumber(phone);
            doctors.add(doctor);
            System.out.println("Doctor added: " + name);
        }
    }

    public static void addDoctor(String name, String specialization, String phone, double consultationFee) {
        Doctor doctor = new Doctor();
        if (name != null && specialization != null && phone != null) {
            doctor.setFirstName(name);
            doctor.setSpecialization(specialization);
            doctor.setPhoneNumber(phone);
            doctor.setConsultationFee(consultationFee);
            doctors.add(doctor);
            System.out.println("Doctor added: " + name);
        }
    }

    public static void assignPatient(String doctorId, String patientId) {
        Doctor doctor = getDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found: " + doctorId);
            return;
        }
        List<Patient> assignedPatients = Doctor.getAssignedPatients();
        if (assignedPatients == null) {
            assignedPatients = new ArrayList<>();
            Doctor.setAssignedPatients(assignedPatients);
        }

        Patient patient = new Patient();
        patient.setPatientId(patientId);
        assignedPatients.add(patient);
        System.out.println("Assigned patient " + patientId + " to doctor " + doctorId);
    }

    public static void assignPatient(Doctor doctor, Patient patient) {
        if (doctor == null || patient == null) {
            System.out.println("Doctor or Patient is null.");
            return;
        }
        List<Patient> assignedPatients = Doctor.getAssignedPatients();
        if (assignedPatients == null) {
            assignedPatients = new ArrayList<>();
            Doctor.setAssignedPatients(assignedPatients);
        }
        assignedPatients.add(patient);
        System.out.println("Assigned " + patient.getPatientId() + " to Dr. " + doctor.getFirstName());
    }

    public static void assignPatient(String doctorId, List<String> patientIds) {
        Doctor doctor = getDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found: " + doctorId);
            return;
        }
        if (patientIds == null || patientIds.isEmpty()) {
            System.out.println("No patient IDs provided for assignment.");
            return;
        }

        List<Patient> assignedPatients = Doctor.getAssignedPatients();
        if (assignedPatients == null) {
            assignedPatients = new ArrayList<>();
            Doctor.setAssignedPatients(assignedPatients);
        }

        for (String p : patientIds) {
            Patient patient = new Patient();
            patient.setPatientId(p);
            assignedPatients.add(patient);
        }

        System.out.println("Bulk assigned " + patientIds.size() + " patients to Dr. " + doctorId);
    }

    public static void displayDoctors(String specialization) {
        boolean found = false;
        System.out.println("----- Doctors in Specialization: " + specialization + " -----");
        for (Doctor d : doctors) {
            if (d.getSpecialization() != null && d.getSpecialization().equalsIgnoreCase(specialization)) {
                d.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No doctors found in specialization: " + specialization);
    }

    public static void displayDoctors(String departmentId, boolean showAvailableOnly) {
        boolean found = false;
        System.out.println("----- Doctors in department: " + departmentId + " -----");
        for (Doctor d : doctors) {
            if (d.getDepartmentId() != null && d.getDepartmentId().equalsIgnoreCase(departmentId)
                    && (!showAvailableOnly || (Doctor.getAvailableSlots() != null &&
                    !Doctor.getAvailableSlots().isEmpty()))) {
                d.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No doctors match.");
        }
    }

    @Override
    public void add(Object entity) {
        if (entity instanceof Doctor) {
            Doctor doctor = (Doctor) entity;
            doctors.add(doctor);
            System.out.println("Doctor added: " + doctor.getFirstName() + " " + doctor.getLastName());
        } else {
            System.out.println("Invalid entity type. Must be Doctor.");
        }
    }

    @Override
    public void remove(String id) {
        boolean removed = doctors.removeIf(d -> d.getDoctorId().equals(id));
        System.out.println(removed
                ? "Doctor removed successfully (ID: " + id + ")"
                : "Doctor not found with ID: " + id);
    }

    @Override
    public void getAll() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }
        System.out.println("All Doctors:");
        for (Doctor d : doctors) {
            d.displaySummary();
        }
        System.out.println("--------------------------------");
    }

    @Override
    public void search(String keyword) {
        if (!HelperUtils.isValidString(keyword)) {
            System.out.println("Invalid search keyword.");
            return;
        }
        boolean found = false;
        for (Doctor d : doctors) {
            if (d.getSpecialization().equalsIgnoreCase(keyword)) {
                System.out.println("Found: " + d.getFirstName() + " - " + d.getSpecialization());
                found = true;
            }
        }
        if (!found) System.out.println("No matching doctors.");

    }

    @Override
    public void searchById(String id) {
        Doctor doctor = getDoctorById(id);
        if (doctor != null) doctor.displayInfo();
        else System.out.println("Doctor not found with ID: " + id);
    }
}


