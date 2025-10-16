package Service;

import Entity.MedicalRecord;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordService {
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();

    public static void addMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecord != null) {
            medicalRecords.add(medicalRecord);
            System.out.println("Medical record is added: " + medicalRecord);
        }
        System.out.println("Cannot add null Medical record ");

    }

    public static void editMedicalRecord(String medicalRecordsId, MedicalRecord updatedmedicalRecord) {
        for (int i = 0; i < medicalRecords.size(); i++) {
            if (medicalRecords.get(i).getRecordId().equals(medicalRecordsId)) {
                medicalRecords.set(i, updatedmedicalRecord);
                System.out.println("MedicalRecord is updated: " + medicalRecordsId);
                return;
            }
        }
        System.out.println("MedicalRecordId not found: " + medicalRecordsId);
    }

    public static void removeMedicalRecord(String medicalRecordsId) {
        boolean removed = medicalRecords.removeIf(m -> m.getRecordId().equals(medicalRecordsId));

        if (removed) {
            System.out.println("medicalRecord is removed: " + medicalRecordsId);
        } else {
            System.out.println("medicalRecord not found: " + medicalRecordsId);
        }
    }

    public static MedicalRecord getMedicalRecordById(String medicalRecordsId) {
        for (MedicalRecord m : medicalRecords) {
            if (m.getRecordId() != null && m.getRecordId().equals(medicalRecordsId)) {
                return m;
            }
        }
        System.out.println("medicalRecord not found: " + medicalRecordsId);
        return null;
    }

    public static void displayAllMedicalRecords() {
        if (medicalRecords.isEmpty()) {
            System.out.println("No medical records available");
        }
        for (MedicalRecord m : medicalRecords) {
            System.out.println(m);
        }
        System.out.println("---------------");
    }

    public static List<MedicalRecord> getRecordsByPatientId(String patientId) {
        List<MedicalRecord> results = new ArrayList<>();

        for (MedicalRecord m : medicalRecords) {
            if (m.getPatientId().equals(patientId)) {
                results.add(m);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No records found for patient:" + patientId);
        } else {
            System.out.println("records found");
        }
        return results;
    }

    public static List<MedicalRecord> getRecordsByDoctorId(String doctorId) {
        List<MedicalRecord> results = new ArrayList<>();

        for (MedicalRecord m : medicalRecords) {
            if (m.getDoctorId().equals(doctorId)) {
                results.add(m);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No records found for doctor:" + doctorId);
        } else {
            System.out.println("records found");
        }
        return results;
    }

    public static void displayPatientHistory(String patientId) {
        List<MedicalRecord> patientRecords = getRecordsByPatientId(patientId);
        if (patientRecords.isEmpty()) return;

        System.out.println("Medical History for Patient: " + patientId );
        for (MedicalRecord r : patientRecords) {
            r.displayInfo();
        }
        System.out.println("===========================");
    }

    public List<MedicalRecord> getAllRecords() {
        return medicalRecords;
    }
}
