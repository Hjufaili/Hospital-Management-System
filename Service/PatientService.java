package Service;

import Entity.Appointment;
import Entity.MedicalRecord;
import Entity.Patient;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PatientService implements Manageable, Searchable {
    private static List<Patient> patients;

    public PatientService() {
        this.patients = new ArrayList<>();
    }

    private List<MedicalRecord> newMedicalRecordList() {
        return new ArrayList<>();
    }

    // Helper method to create a new, initialized list of Appointment objects
    private List<Appointment> newAppointmentList() {
        return new ArrayList<>();
    }


    public void addPatient(Patient patient) {
        if (HelperUtils.isNull(patient) || !HelperUtils.isValidString(patient.getPatientId())) {
            System.err.println("Cannot add patient. Entity is null or ID is invalid.");
            return;
        }

        for (Patient existingPatient : patients) {
            if (existingPatient.getPatientId().equals(patient.getPatientId())) {
                System.err.println("Cannot add patient. ID already exists: " + patient.getPatientId());
                return;
            }
        }

        patients.add(patient);
        System.out.println("✅ Patient added successfully: " + patient.getFirstName() + " " + patient.getLastName());
    }

    public void editPatient(String patientId, Patient updatedPatient) {
        if (!HelperUtils.isValidString(patientId) || HelperUtils.isNull(updatedPatient)) {
            System.err.println("Invalid ID or update object provided for editing.");
            return;
        }

        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getPatientId().equals(patientId)) {
                patients.set(i, updatedPatient);
                System.out.println(" updated successfully!");
                return;
            }
        }
        System.out.println("Patient not found: " + patientId);
    }

    public static void removePatient(String patientId) {

        if (!HelperUtils.isValidString(patientId)) {
            System.err.println("Invalid Patient ID provided for removal.");
            return;
        }


        boolean removed = patients.removeIf(p -> p.getPatientId().equals(patientId));
        if (removed)
            System.out.println("Patient removed: " + patientId);
        else
            System.out.println("Patient not found: " + patientId);
    }

    public static Patient getPatientById(String patientId) {
        for (Patient p : patients) {
            if (p.getPatientId().equals(patientId)) {
                return p;
            }
        }
        System.out.println("Patient not found:" + patientId);
        return null;
    }

    public static void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients available.");
            return;
        }
        System.out.println("All patients ");
        for (Patient p : patients) {
            System.out.println(p);
        }
        System.out.println("------------------");
    }

    public List<Patient> getAllPatients() {
        return patients;
    }

    public void addPatient(String firstName, String lastName, String phone) {
        if (HelperUtils.isValidString(firstName) && HelperUtils.isValidString(lastName) && HelperUtils.isValidString(phone, 8, 8)) {

            Patient newPatient = new Patient(firstName, lastName, phone, "N/A", "Unknown");
            addPatient(newPatient);
            System.out.println("Patient added (minimal info): " + firstName + " " + lastName);

        } else {
            System.err.println("Failed to add patient: Invalid first name, last name, or phone number.");
        }
    }

    public void addPatient(String firstName, String lastName, String phone,
                           String bloodGroup, String email) {
        if (HelperUtils.isValidString(firstName) && HelperUtils.isValidString(lastName) && HelperUtils.isValidString(phone, 8, 8)) {

            Patient newPatient = new Patient(firstName, lastName, phone, email, bloodGroup);

            addPatient(newPatient);
            System.out.println("Patient added (with blood group): " + firstName + " " + lastName);
        } else {
            System.err.println("Failed to add patient: Validation error in provided details.");
        }
    }

    public static List<Patient> searchPatients(String keyword) {
        List<Patient> results = new ArrayList<>();
        keyword = keyword.toLowerCase();
        for (Patient p : patients) {
            if (
                    (p.getPatientId() != null && p.getPatientId().toLowerCase().contains(keyword)) ||
                            (p.getFirstName() != null && p.getFirstName().toLowerCase().contains(keyword)) ||
                            (p.getLastName() != null && p.getLastName().toLowerCase().contains(keyword)) ||
                            (p.getEmail() != null && p.getEmail().toLowerCase().contains(keyword)) ||
                            (p.getGender() != null && p.getGender().toLowerCase().contains(keyword)) ||
                            (p.getPhoneNumber() != null && p.getPhoneNumber().toLowerCase().contains(keyword)) ||
                            (p.getBloodGroup() != null && p.getBloodGroup().toLowerCase().contains(keyword)) ||
                            (p.getEmergencyContact() != null && p.getEmergencyContact().toLowerCase().contains(keyword))) {
                results.add(p);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No patient found with : " + keyword);
        }
        return results;
    }

    public static Patient searchPatients(String firstName, String lastName) {
        for (Patient p : patients) {
            if (p.getFirstName().equalsIgnoreCase(firstName)
                    && p.getLastName().equalsIgnoreCase(lastName)) {
                return p;
            }

        }
        System.out.println("No patient found with name: " + firstName + " " + lastName);
        return null;
    }

    public static void displayPatients(String filter) {
        List<Patient> filtered = searchPatients(filter);
        if (filtered.isEmpty()) {
            System.out.println("No patients match the filter: " + filter);
            return;
        }
        System.out.println("Filtered Patients (" + filter + "):");
        for (Patient p : filtered) {
            System.out.println(p);
        }
        System.out.println("--------------------");
    }

    public static void displayPatients(int limit) {
        if (patients.isEmpty()) {
            System.out.println("No patients available.");
            return;
        }

        System.out.println("Displaying first " + limit + " patients:");
        for (int i = 0; i < Math.min(limit, patients.size()); i++) {
            System.out.println(patients.get(i));
        }
        System.out.println("--------------------");
    }


    @Override
    public void add(Object entity) {

        if (entity instanceof Patient) {
            addPatient((Patient) entity);
        } else if (HelperUtils.isNull(entity)) {
            System.err.println("Entity is null.");
        } else {
            System.err.println("Entity is not a Patient type.");
        }
    }

    @Override
    public void remove(String id) {
        removePatient(id);
    }

    @Override
    public void getAll() {
        displayAllPatients();

    }

    @Override
    public void search(String keyword) {
        List<Patient> results = searchPatients(keyword);
        if (results.isEmpty()) {
            System.out.println("No patient found with keyword: " + keyword);
        } else {
            System.out.println("--- Search Results for '" + keyword + "' (" + results.size() + ") ---");
            results.forEach(Patient::displaySummary);
            System.out.println("------------------------------------");
        }
    }

    @Override
    public void searchById(String id) {
        Patient patient = getPatientById(id);
        if (HelperUtils.isNotNull(patient)) {
            System.out.println("Patient Found by ID: " + id);
            patient.displayInfo();
        }

    }

}
