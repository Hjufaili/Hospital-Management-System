package Entity;

import java.time.LocalDate;
import java.util.List;

public class Patient extends Person {

    private String patientId;
    private String bloodGroup;
    private List<String> allergies;
    private String emergencyContact;
    private LocalDate registrationDate;
    private String insuranceId;
    private List<MedicalRecord> medicalRecords;
    private List<Appointment> appointments;

    public Patient(String id, String firstName, String lastName, LocalDate dateOfBirth,
                   String gender, String phoneNumber, String address, String email,
                   String patientId, String bloodGroup, List<String> allergies, String emergencyContact,
                   LocalDate registrationDate, String insuranceId,
                   List<MedicalRecord> medicalRecords, List<Appointment> appointments) {

        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email);
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.allergies = allergies;
        this.emergencyContact = emergencyContact;
        this.registrationDate = registrationDate;
        this.insuranceId = insuranceId;
        this.medicalRecords = medicalRecords;
        this.appointments = appointments;
    }

    public Patient(String patientId, String bloodGroup, List<String> allergies, String emergencyContact,
                   LocalDate registrationDate, String insuranceId,
                   List<MedicalRecord> medicalRecords, List<Appointment> appointments) {
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.allergies = allergies;
        this.emergencyContact = emergencyContact;
        this.registrationDate = registrationDate;
        this.insuranceId = insuranceId;
        this.medicalRecords = medicalRecords;
        this.appointments = appointments;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("patientId:           " + patientId);
        System.out.println("bloodGroup:         " + bloodGroup );
        System.out.println("allergies:         " + allergies );
        System.out.println("emergencyContact:         " + emergencyContact );
        System.out.println("registrationDate:         " + registrationDate );
        System.out.println("insuranceId:         " + insuranceId );
        System.out.println("medicalRecords:         " + medicalRecords );
        System.out.println("appointments:         " + appointments );

    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", allergies=" + allergies +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", registrationDate=" + registrationDate +
                ", insuranceId='" + insuranceId + '\'' +
                ", medicalRecords=" + medicalRecords +
                ", appointments=" + appointments +
                '}';
    }

}
