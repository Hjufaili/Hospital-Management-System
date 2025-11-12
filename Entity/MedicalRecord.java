package Entity;

import Interface.Displayable;
import Utils.HelperUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
public class MedicalRecord implements Displayable {
    private String recordId = HelperUtils.generateId("MR");
    private String patientId;
    private String doctorId;
    private LocalDateTime visitDate = LocalDateTime.now();
    private String diagnosis;
    private String prescription;
    private String testResults = "Pending";
    private String notes;

//    public MedicalRecord() {
//        this.recordId = HelperUtils.generateId("MR");
//        this.visitDate = LocalDateTime.now();
//    }

//    public MedicalRecord(String recordId, String patientId, String doctorId, LocalDateTime visitDate,
//                         String diagnosis, String prescription, String testResults, String notes) {
//        setRecordId(recordId);
//        setPatientId(patientId);
//        setDoctorId(doctorId);
//        setVisitDate(visitDate);
//        setDiagnosis(diagnosis);
//        setPrescription(prescription);
//        setTestResults(testResults);
//        setNotes(notes);
//    }
//
//    public MedicalRecord(String patientId, String doctorId, String diagnosis, String prescription, String notes) {
//        this(); // Calls default constructor to auto-generate ID and set visitDate to now
//        setPatientId(patientId);
//        setDoctorId(doctorId);
//        setDiagnosis(diagnosis);
//        setPrescription(prescription);
//        setNotes(notes);
//        this.testResults = "Pending"; // Default status
//    }


    public void setRecordId(String recordId) {
        if (HelperUtils.isValidString(recordId, 5, 20)) {
            this.recordId = recordId;
        } else {
            System.err.println("Validation Error: Invalid Record ID.");
        }
    }

    public void setPatientId(String patientId) {
        if (HelperUtils.isValidString(patientId, 5, 20)) {
            this.patientId = patientId;
        } else {
            System.err.println("Validation Error: Invalid Patient ID for record.");
        }
    }

    public void setDoctorId(String doctorId) {
        if (HelperUtils.isValidString(doctorId, 5, 20)) {
            this.doctorId = doctorId;
        } else {
            System.err.println("Validation Error: Invalid Doctor ID for record.");
        }
    }

    public void setVisitDate(LocalDateTime visitDate) {
        if (HelperUtils.isNotNull(visitDate) && visitDate.isBefore(LocalDateTime.now().plusSeconds(1))) {
            this.visitDate = visitDate;
        } else {
            System.err.println("Validation Error: Visit date cannot be null or in the future.");
        }
    }

    public void setDiagnosis(String diagnosis) {
        if (HelperUtils.isValidString(diagnosis, 5)) { // Min 5 chars for meaningful diagnosis
            this.diagnosis = diagnosis;
        } else {
            System.err.println("Validation Error: Diagnosis cannot be empty or too short.");
        }
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void displayInfo() {
        System.out.println("recordId:     " + recordId);
        System.out.println("patientId:     " + patientId);
        System.out.println("doctorId:     " + doctorId);
        System.out.println("visitDate:     " + visitDate);
        System.out.println("diagnosis:     " + diagnosis);
        System.out.println("prescription:     " + prescription);
        System.out.println("testResults:     " + testResults);
        System.out.println("notes:     " + notes);

    }

    @Override
    public void displaySummary() {
        System.out.println("Record " + recordId + " | Patient: " + patientId +
                " | Doctor: " + doctorId + " | Date: " + visitDate);
    }

//    @Override
//    public String toString() {
//        return "MedicalRecord{" +
//                "recordId='" + recordId + '\'' +
//                ", patientId='" + patientId + '\'' +
//                ", doctorId='" + doctorId + '\'' +
//                ", visitDate=" + visitDate +
//                ", diagnosis='" + diagnosis + '\'' +
//                ", prescription='" + prescription + '\'' +
//                ", testResults='" + testResults + '\'' +
//                ", notes='" + notes + '\'' +
//                '}';
//    }

    public void updateDiagnosis(String newDiagnosis) {
        setDiagnosis(newDiagnosis); // Use validated setter
        System.out.println("Diagnosis updated for Record " + recordId);
    }


    public void updateTestResults(String newResults) {
        setTestResults(newResults);
        System.out.println("Test results updated for Record " + recordId);
    }

    public void updateTestResults(String newResults, String additionalNote) {
        setTestResults(newResults);
        if (HelperUtils.isValidString(additionalNote)) {
            this.notes = (this.notes != null ? this.notes + "\n--- Update Note: " : "Update Note: ") + additionalNote;
        }
        System.out.println("Test results and note updated for Record " + recordId);
    }
}
