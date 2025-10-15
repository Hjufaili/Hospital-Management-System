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

}
