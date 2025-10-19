package Entity;

import java.time.LocalDate;
import java.util.List;

public class OutPatient extends Patient{
    private Integer visitCount;
    private LocalDate lastVisitDate;
    private String preferredDoctorId;

    public OutPatient(Integer visitCount, LocalDate lastVisitDate, String preferredDoctorId) {
        this.visitCount = visitCount;
        this.lastVisitDate = lastVisitDate;
        this.preferredDoctorId = preferredDoctorId;
    }

    public OutPatient(String id, String firstName, String lastName, LocalDate dateOfBirth,
                      String gender, String phoneNumber, String address, String email,
                      String patientId, String bloodGroup, List<String> allergies,
                      String emergencyContact, LocalDate registrationDate, String insuranceId,
                      List<MedicalRecord> medicalRecords, List<Appointment> appointments,
                      Integer visitCount, LocalDate lastVisitDate, String preferredDoctorId) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email,
                patientId, bloodGroup, allergies, emergencyContact, registrationDate,
                insuranceId, medicalRecords, appointments);
        this.visitCount = visitCount;
        this.lastVisitDate = lastVisitDate;
        this.preferredDoctorId = preferredDoctorId;
    }

    public OutPatient(String patientId, String bloodGroup, List<String> allergies,
                      String emergencyContact, LocalDate registrationDate, String insuranceId,
                      List<MedicalRecord> medicalRecords, List<Appointment> appointments,
                      Integer visitCount, LocalDate lastVisitDate, String preferredDoctorId) {
        super(patientId, bloodGroup, allergies, emergencyContact, registrationDate,
                insuranceId, medicalRecords, appointments);
        this.visitCount = visitCount;
        this.lastVisitDate = lastVisitDate;
        this.preferredDoctorId = preferredDoctorId;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public String getPreferredDoctorId() {
        return preferredDoctorId;
    }

    public void setPreferredDoctorId(String preferredDoctorId) {
        this.preferredDoctorId = preferredDoctorId;
    }

    public void scheduleFollowUp(LocalDate nextVisitDate) {
        System.out.println("Follow-up visit for " + getFirstName() +
                " scheduled on " + nextVisitDate + " with doctor ID: " + preferredDoctorId);
    }

    public void updateVisitCount(LocalDate newVisitDate) {
        visitCount++;
        lastVisitDate = newVisitDate;
        System.out.println("Visit count for " + getFirstName() +
                " updated to " + visitCount + ". Last visit date: " + lastVisitDate);
    }
}
