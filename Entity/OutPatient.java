package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OutPatient extends Patient implements Displayable {
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

    public OutPatient(String firstName, String lastName, String gender, String phoneNumber,
                      String bloodGroup, String preferredDoctorId) {


        super(
                HelperUtils.generateId("PER"), firstName, lastName, (LocalDate) null,
                gender, phoneNumber,
                "N/A",
                "N/A",

                HelperUtils.generateId("PAT"), bloodGroup,
                new ArrayList<String>(),
                "N/A",
                LocalDate.now(),
                "N/A",
                new ArrayList<MedicalRecord>(),
                new ArrayList<Appointment>()
        );

        this.visitCount = 1;
        this.lastVisitDate = LocalDate.now();
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


    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Visit Count: " + visitCount);
        System.out.println("Last Visit Date: " + lastVisitDate);
        System.out.println("Preferred Doctor ID: " + preferredDoctorId);
    }

    @Override
    public void displaySummary() {
        System.out.println("OutPatient " + getFirstName() + " " + getLastName() +
                "Visit Count: " + visitCount + "Last Visit Date: " + lastVisitDate);
    }
}
