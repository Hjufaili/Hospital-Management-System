package Service;

import Entity.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PatientService {
    private static List<Patient> patients = new ArrayList<>();

    public static void addPatient(Patient patient) {
        if (patient != null) {
            patients.add(patient);
            System.out.println("Patient added: ");
        } else {
            System.out.println("Cannot null patient.");
        }
    }

    public void editPatient(String patientId, Patient updatedPatient) {
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

    public static void addPatient(String firstName, String lastName, String phone) {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPhoneNumber(phone);
        patients.add(patient);
        System.out.println("Patient added (minimal info): " + firstName + " " + lastName);
    }

    public static Patient searchPatientsByName(String firstName, String lastName) {
        for (Patient p : patients) {
            if (p.getFirstName().equalsIgnoreCase(firstName)
                    && p.getLastName().equalsIgnoreCase(lastName)) {
                return p;
            }

        }
        System.out.println("No patient found with name: " + firstName + " " + lastName);
        return null;
    }

    public static List<Patient> searchPatients(String keyword) {
        List<Patient> results = new ArrayList<>();
        keyword=keyword.toLowerCase();
        for (Patient p : patients) {
            if (p.getPatientId().toLowerCase().contains(keyword) ||
                    p.getFirstName().toLowerCase().contains(keyword) ||
                    p.getLastName().toLowerCase().contains(keyword) ||
                    p.getEmail().toLowerCase().contains(keyword) ||
                    p.getGender().toLowerCase().contains(keyword) ||
                    p.getPhoneNumber().toLowerCase().contains(keyword) ||
                    p.getBloodGroup().toLowerCase().contains(keyword) ||
                    p.getEmergencyContact().toLowerCase().contains(keyword)) {
                results.add(p);
            }
            results.add(p);
        }
        System.out.println("No patient found with : " + keyword);
        return null;
    }
}
