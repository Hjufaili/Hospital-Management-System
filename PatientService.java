package Service;

import Entity.Patient;

import java.util.ArrayList;
import java.util.List;

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
        for (Patient p:patients) {
            if (p.getPatientId().equals(patientId)){
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
        for (Patient p:patients){
            System.out.println(p);
        }
        System.out.println("------------------");
    }

}
