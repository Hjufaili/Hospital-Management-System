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
}
