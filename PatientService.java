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
}
