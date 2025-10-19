package Entity;

import java.time.LocalDate;
import java.util.List;

public class Consultant extends Doctor{
    private List<String> consultationTypes;
    private Boolean onlineConsultationAvailable;
    private Integer consultationDuration;

    public Consultant(List<String> consultationTypes, Boolean onlineConsultationAvailable,
                      Integer consultationDuration) {
        this.consultationTypes = consultationTypes;
        this.onlineConsultationAvailable = onlineConsultationAvailable;
        this.consultationDuration = consultationDuration;
    }

    public Consultant(String id, String firstName, String lastName, LocalDate dateOfBirth,
                      String gender, String phoneNumber, String address, String email,
                      String doctorId, String specialization, String qualification,
                      Integer experienceYears, String departmentId, Double consultationFee,
                      List<String> availableSlots, List<Patient> assignedPatients,
                      List<String> consultationTypes, Boolean onlineConsultationAvailable,
                      Integer consultationDuration) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email,
                doctorId, specialization, qualification, experienceYears, departmentId,
                consultationFee, availableSlots, assignedPatients);
        this.consultationTypes = consultationTypes;
        this.onlineConsultationAvailable = onlineConsultationAvailable;
        this.consultationDuration = consultationDuration;
    }

    public Consultant(String doctorId, String specialization, String qualification,
                      Integer experienceYears, String departmentId, Double consultationFee,
                      List<String> availableSlots, List<Patient> assignedPatients,
                      List<String> consultationTypes, Boolean onlineConsultationAvailable,
                      Integer consultationDuration) {
        super(doctorId, specialization, qualification, experienceYears, departmentId,
                consultationFee, availableSlots, assignedPatients);
        this.consultationTypes = consultationTypes;
        this.onlineConsultationAvailable = onlineConsultationAvailable;
        this.consultationDuration = consultationDuration;
    }

    public List<String> getConsultationTypes() {
        return consultationTypes;
    }

    public void setConsultationTypes(List<String> consultationTypes) {
        this.consultationTypes = consultationTypes;
    }

    public Boolean getOnlineConsultationAvailable() {
        return onlineConsultationAvailable;
    }

    public void setOnlineConsultationAvailable(Boolean onlineConsultationAvailable) {
        this.onlineConsultationAvailable = onlineConsultationAvailable;
    }

    public Integer getConsultationDuration() {
        return consultationDuration;
    }

    public void setConsultationDuration(Integer consultationDuration) {
        this.consultationDuration = consultationDuration;
    }

    public void scheduleConsultation(String type) {
        if (!consultationTypes.contains(type)) {
            System.out.println( "does not provide consultations in: " + type);
        } else {
            System.out.println("scheduled a " + type +
                    " consultation. Duration: " + consultationDuration + " minutes.");
        }


    }

    public void provideSecondOpinion(String diagnosis) {
        System.out.println("provided a second opinion on diagnosis: " + diagnosis);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Consultation Types: " + consultationTypes);
        System.out.println("Online Consultation Available: " + (onlineConsultationAvailable ? "Yes" : "No"));
        System.out.println("Consultation Duration: " + consultationDuration + " minutes");
    }

}
