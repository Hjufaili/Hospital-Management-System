package Service;

import Entity.MedicalRecord;
import Entity.Nurse;

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
            if (m.getRecordId() !=null && m.getRecordId().equals(medicalRecordsId)){
                return m;
            }
        }
        System.out.println("medicalRecord not found: " + medicalRecordsId);
        return null;
    }

    public static void displayAllMedicalRecords() {
        if (medicalRecords.isEmpty()){
            System.out.println("No medical records available");
        }
        for (MedicalRecord m:medicalRecords){
            System.out.println(m);
        }
        System.out.println("---------------");
    }
}
