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
            System.out.println("Medical record is added: "+medicalRecord);
        }
        System.out.println("Cannot add null Medical record ");

    }
}
