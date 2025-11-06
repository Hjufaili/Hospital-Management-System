package Service;

import Entity.MedicalRecord;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MedicalRecordService implements Manageable, Searchable {
    private List<MedicalRecord> medicalRecords;

    public MedicalRecordService (){
        this.medicalRecords=new ArrayList<>();
    }

    public  void addMedicalRecord(MedicalRecord medicalRecord) {
        if (HelperUtils.isNull(medicalRecord) || !HelperUtils.isValidString(medicalRecord.getRecordId())) {
            System.err.println("Cannot add null medical record or one with an invalid ID.");
            return;
        }

        for (MedicalRecord existingRecord : medicalRecords) {
            if (existingRecord.getRecordId().equals(medicalRecord.getRecordId())) {
                System.err.println("Cannot add medical record. ID already exists: " + medicalRecord.getRecordId());
                return;
            }
        }

        medicalRecords.add(medicalRecord);
        System.out.println("Medical record added: " + medicalRecord.getRecordId());

    }

    public void addMedicalRecord(String patientId, String doctorId, String diagnosis) {
        if (HelperUtils.isValidString(patientId) && HelperUtils.isValidString(doctorId) && HelperUtils.isValidString(diagnosis)) {

            MedicalRecord newRecord = new MedicalRecord(
                    HelperUtils.generateId("REC"), patientId, doctorId,
                    LocalDate.now(), diagnosis, "Initial notes");

            addMedicalRecord(newRecord);
        } else {
            System.err.println("Failed to add medical record: Patient ID, Doctor ID, or Diagnosis is invalid.");
        }
    }


    public  void editMedicalRecord(String medicalRecordsId, MedicalRecord updatedmedicalRecord) {
        if (!HelperUtils.isValidString(medicalRecordsId) || HelperUtils.isNull(updatedmedicalRecord)) {
            System.err.println("Invalid ID or update object provided for editing.");
            return;
        }

        for (int i = 0; i < medicalRecords.size(); i++) {
            if (medicalRecords.get(i).getRecordId().equals(medicalRecordsId)) {
                medicalRecords.set(i, updatedmedicalRecord);
                System.out.println("MedicalRecord is updated: " + medicalRecordsId);
                return;
            }
        }
        System.out.println("MedicalRecordId not found: " + medicalRecordsId);
    }

    public  void removeMedicalRecord(String medicalRecordsId) {
        if (!HelperUtils.isValidString(medicalRecordsId)) {
            System.err.println("Invalid Medical Record ID provided for removal.");
            return;
        }

        boolean removed = medicalRecords.removeIf(m -> m.getRecordId().equals(medicalRecordsId));

        if (removed) {
            System.out.println("medicalRecord is removed: " + medicalRecordsId);
        } else {
            System.out.println("medicalRecord not found: " + medicalRecordsId);
        }
    }

    public  MedicalRecord getMedicalRecordById(String medicalRecordsId) {

        for (MedicalRecord m : medicalRecords) {
            if (m.getRecordId() != null && m.getRecordId().equals(medicalRecordsId)) {
                return m;
            }
        }
        System.out.println("medicalRecord not found: " + medicalRecordsId);
        return null;
    }

    public  void displayAllMedicalRecords() {
        if (medicalRecords.isEmpty()) {
            System.out.println("No medical records available");
            return;
        }
        for (MedicalRecord m : medicalRecords) {
            m.displaySummary();
        }
        System.out.println("---------------");
    }

    public  List<MedicalRecord> getRecordsByPatientId(String patientId) {
        if (!HelperUtils.isValidString(patientId)) return new ArrayList<>();

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

    public  List<MedicalRecord> getRecordsByDoctorId(String doctorId) {
        if (!HelperUtils.isValidString(doctorId)) return new ArrayList<>();

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

    public void displayPatientHistory(String patientId) {
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
            addMedicalRecord((MedicalRecord) entity);
        } else {
            System.err.println("Invalid entity type. Must be MedicalRecord.");
        }
    }

    @Override
    public void remove(String id) {
        removeMedicalRecord(id);
    }

    @Override
    public void getAll() {
        displayAllMedicalRecords();
    }

    @Override
    public void search(String keyword) {
        System.out.println("Searching medical records for keyword: " + keyword);

        if (!HelperUtils.isValidString(keyword)) return;

        final String lowerKeyword = keyword.toLowerCase(Locale.ROOT);
        List<MedicalRecord> results = new ArrayList<>();

        for (MedicalRecord r : medicalRecords) {
            if ((r.getDiagnosis() != null && r.getDiagnosis().toLowerCase().contains(lowerKeyword)) ||
                    (r.getNotes() != null && r.getNotes().toLowerCase().contains(lowerKeyword))) {
                results.add(r);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No records found for: " + keyword);
        } else {
            System.out.println("--- Search Results for '" + keyword + "' (" + results.size() + ") ---");
            for (MedicalRecord r : results) {
                r.displaySummary();
            }
            System.out.println("--------------------------------");
        }
    }

    @Override
    public void searchById(String id) {
        MedicalRecord record = getMedicalRecordById(id);
        if (record != null) record.displayInfo();
        else System.out.println("Record not found with ID: " + id);
    }
}
