package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Patient extends Person implements Displayable {

    private String patientId=HelperUtils.generateId("DOC") ;
    private String bloodGroup;
    private List<String> allergies;
    private String emergencyContact;
    private LocalDate registrationDate;
    private String insuranceId;
    private List<MedicalRecord> medicalRecords;
    private List<Appointment> appointments;

    public Patient() {
    }

    public Patient(String id, String firstName, String lastName, LocalDate dateOfBirth,
                   String gender, String phoneNumber, String address, String email,
                   String patientId, String bloodGroup, List<String> allergies, String emergencyContact,
                   LocalDate registrationDate, String insuranceId,
                   List<MedicalRecord> medicalRecords, List<Appointment> appointments) {

        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email);
        this.patientId = HelperUtils.generateId("DOC");
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
        this.patientId = HelperUtils.generateId("DOC");
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
        System.out.println("bloodGroup:         " + bloodGroup);
        System.out.println("allergies:         " + allergies);
        System.out.println("emergencyContact:         " + emergencyContact);
        System.out.println("registrationDate:         " + registrationDate);
        System.out.println("insuranceId:         " + insuranceId);
        System.out.println("medicalRecords:         " + medicalRecords);
        System.out.println("appointments:         " + appointments);

    }

    @Override
    public void displaySummary() {
        System.out.println("Patient: " + Person.getFirstName() + " " + Person.getLastName() +
                " | ID: " + patientId + " | Blood: " + bloodGroup);
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

    public void addMedicalRecord(MedicalRecord record) {
        if (record != null) {
            medicalRecords.add(record);
            System.out.println("Medical record added for patient " + getFirstName());
        }
    }

    public void addAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.add(appointment);
            System.out.println("Appointment added for patient " + getFirstName());
        }
    }

    public void updateInsurance(String newInsuranceId) {
        this.insuranceId = newInsuranceId;
        System.out.println("Insurance updated for patient " + getFirstName());
    }

    public void updateContact(String phone) {
        setPhoneNumber(phone);
        System.out.println("Updated phone number for patient " + getFirstName() + " to: " + phone);
    }

    public void updateContact(String phone, String email) {
        setPhoneNumber(phone);
        setEmail(email);
        System.out.println("Updated for patient " + getFirstName() + "new phone"+ phone+
                "new email: "+ email);
    }

    public void updateContact(String phone, String email, String address) {
        setPhoneNumber(phone);
        setEmail(email);
        setAddress(address);
        System.out.println("Updated for patient " + getFirstName() + "new phone"+ phone+
                "new email: "+ email + "new address: "+ address);
    }
}
