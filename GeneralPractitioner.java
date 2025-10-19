package Entity;

import java.time.LocalDate;
import java.util.List;

public class GeneralPractitioner extends Doctor{

    private Boolean walkinAvailable;
    private Boolean homeVisitAvailable;
    private Boolean vaccinationCertified;

    public GeneralPractitioner(Boolean walkinAvailable, Boolean homeVisitAvailable, Boolean vaccinationCertified) {
        this.walkinAvailable = walkinAvailable;
        this.homeVisitAvailable = homeVisitAvailable;
        this.vaccinationCertified = vaccinationCertified;
    }

    public GeneralPractitioner(String id, String firstName, String lastName, LocalDate dateOfBirth,
                               String gender, String phoneNumber, String address, String email,
                               String doctorId, String specialization, String qualification,
                               Integer experienceYears, String departmentId, Double consultationFee,
                               List<String> availableSlots, List<Patient> assignedPatients,
                               Boolean walkinAvailable, Boolean homeVisitAvailable,
                               Boolean vaccinationCertified) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email, doctorId,
                specialization, qualification, experienceYears, departmentId, consultationFee,
                availableSlots, assignedPatients);
        this.walkinAvailable = walkinAvailable;
        this.homeVisitAvailable = homeVisitAvailable;
        this.vaccinationCertified = vaccinationCertified;
    }

    public GeneralPractitioner(String doctorId, String specialization, String qualification,
                               Integer experienceYears, String departmentId, Double consultationFee,
                               List<String> availableSlots, List<Patient> assignedPatients,
                               Boolean walkinAvailable, Boolean homeVisitAvailable, Boolean vaccinationCertified) {
        super(doctorId, specialization, qualification, experienceYears, departmentId, consultationFee,
                availableSlots, assignedPatients);
        this.walkinAvailable = walkinAvailable;
        this.homeVisitAvailable = homeVisitAvailable;
        this.vaccinationCertified = vaccinationCertified;
    }

    public Boolean getWalkinAvailable() {
        return walkinAvailable;
    }

    public void setWalkinAvailable(Boolean walkinAvailable) {
        this.walkinAvailable = walkinAvailable;
    }

    public Boolean getHomeVisitAvailable() {
        return homeVisitAvailable;
    }

    public void setHomeVisitAvailable(Boolean homeVisitAvailable) {
        this.homeVisitAvailable = homeVisitAvailable;
    }

    public Boolean getVaccinationCertified() {
        return vaccinationCertified;
    }

    public void setVaccinationCertified(Boolean vaccinationCertified) {
        this.vaccinationCertified = vaccinationCertified;
    }

    public void scheduleHomeVisit(String patientName, String address) {
        if (homeVisitAvailable) {
            System.out.println(" scheduled a home visit for patient " +
                    patientName + " at address: " + address);
        } else {
            System.out.println(" does not offer home visits.");
        }
    }

    public void administerVaccine(String patientName, String vaccineName) {
        if (vaccinationCertified) {
            System.out.println(" administered the " + vaccineName +
                    " vaccine to patient " + patientName);
        } else {
            System.out.println(" is not certified to administer vaccines.");
        }
    }
}
