package Entity;

import Interface.Displayable;

import java.time.LocalDate;
import java.util.Date;

public class MedicalRecord implements Displayable {
    private String recordId;
    private String patientId;
    private String doctorId;
    private Date visitDate;
    private String diagnosis;
    private String prescription;
    private String testResults;
    private String notes;

    public MedicalRecord() {
    }

    public MedicalRecord(String recordId, String patientId, String doctorId, Date visitDate,
                         String diagnosis, String prescription, String testResults, String notes) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.testResults = testResults;
        this.notes = notes;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    public String getNotes() {
        return notes;
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

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId='" + recordId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", visitDate=" + visitDate +
                ", diagnosis='" + diagnosis + '\'' +
                ", prescription='" + prescription + '\'' +
                ", testResults='" + testResults + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
