package Service;

import Entity.MedicalRecord;
import Interface.Manageable;
import Interface.Searchable;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordService implements Manageable, Searchable {
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
        if (patientRecords.isEmpty()) {
            return;
        }

        System.out.println("Medical History for Patient: " + patientId);
        for (MedicalRecord r : patientRecords) {
            r.displayInfo();
        }
        System.out.println("========================");
    }

    public List<MedicalRecord> getAllRecords() {
        return medicalRecords;
    }

    @Override
    public void add(Object entity) {
        if (entity instanceof MedicalRecord) {
            MedicalRecord record = (MedicalRecord) entity;
            medicalRecords.add(record);
            System.out.println("Medical record added: " + record.getRecordId());
        } else {
            System.out.println("Invalid entity type. Must be MedicalRecord.");
        }
    }

    @Override
    public void remove(String id) {
        boolean removed = medicalRecords.removeIf(r -> r.getRecordId().equals(id));
        System.out.println(removed
                ? "Record removed successfully (ID: " + id + ")"
                : "Record not found with ID: " + id);
    }

    @Override
    public void getAll() {
        if (medicalRecords.isEmpty()) {
            System.out.println("No medical records available.");
            return;
        }
        System.out.println("All Medical Records:");
        for (MedicalRecord r : medicalRecords) {
            r.displaySummary();
        }
        System.out.println("--------------------------------");
    }

    @Override
    public void search(String keyword) {
        System.out.println("Searching medical records for keyword: " + keyword);
        boolean found = false;
        for (MedicalRecord r : medicalRecords) {
            if ((r.getDiagnosis() != null && r.getDiagnosis().toLowerCase().contains(keyword.toLowerCase())) ||
                    (r.getNotes() != null && r.getNotes().toLowerCase().contains(keyword.toLowerCase()))) {
                r.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No records found for: " + keyword);
    }

    @Override
    public void searchById(String id) {
        MedicalRecord record = getMedicalRecordById(id);
        if (record != null) record.displayInfo();
        else System.out.println("Record not found with ID: " + id);
    }
}
