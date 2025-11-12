package Entity;

import Interface.Displayable;
import Utils.HelperUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true, exclude = {"medicalRecords", "appointments"})
public class Patient extends Person implements Displayable {

    @ToString.Exclude
    private String patientId = HelperUtils.generateId("PAT");
    private String bloodGroup;
    private List<String> allergies = new ArrayList<>();
    private String emergencyContact;
    private LocalDate registrationDate = LocalDate.now();
    private String insuranceId;
    @ToString.Exclude
    private List<MedicalRecord> medicalRecords = new ArrayList<>();
    @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>();


    public Patient(String id, String firstName, String lastName, LocalDate dateOfBirth,
                   String gender, String phoneNumber, String email, String address,
                   String patientId, String bloodGroup, List<String> allergies, String emergencyContact,
                   LocalDate registrationDate, String insuranceId) {

        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email);
        setPatientId(patientId);
        setBloodGroup(bloodGroup);
        setAllergies(allergies);
        setEmergencyContact(emergencyContact);
        setRegistrationDate(registrationDate);
        setInsuranceId(insuranceId);
        this.medicalRecords = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public Patient(String firstName, String lastName, LocalDate dateOfBirth, String gender,
                   String phoneNumber, String email, String address, String bloodGroup,
                   String emergencyContact, String insuranceId) {

        super(HelperUtils.generateId("PER"), firstName, lastName, dateOfBirth,
                gender, phoneNumber, email, address);

        this.patientId = HelperUtils.generateId("PAT");

        setBloodGroup(bloodGroup);
        setEmergencyContact(emergencyContact);
        setInsuranceId(insuranceId);

        this.registrationDate = LocalDate.now();
        this.allergies = new ArrayList<>();
        this.medicalRecords = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }


    public Patient(String bloodGroup, List<String> allergies, String emergencyContact,
                   LocalDate registrationDate, String insuranceId,
                   List<MedicalRecord> medicalRecords, List<Appointment> appointments) {
        super();
        this.patientId = HelperUtils.generateId("PAT");
        this.bloodGroup = bloodGroup;
        this.allergies = allergies;
        this.emergencyContact = emergencyContact;
        this.registrationDate = registrationDate;
        this.insuranceId = insuranceId;
        this.medicalRecords = (medicalRecords != null) ? medicalRecords : new ArrayList<>();
        this.appointments = (appointments != null) ? appointments : new ArrayList<>();

    }

    public Patient(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender,
                   String phoneNumber, String email, String address, // Assumed Person fields order
                   String patientId, String bloodGroup, List<String> allergies, String emergencyContact,
                   LocalDate registrationDate, String insuranceId, List<MedicalRecord> medicalRecords,
                   List<Appointment> appointments) {

        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address);

        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.allergies = allergies;
        this.emergencyContact = emergencyContact;
        this.registrationDate = registrationDate;
        this.insuranceId = insuranceId;
        this.medicalRecords = medicalRecords;
        this.appointments = appointments;
    }

    public Patient(String first, String last, String gender, String phone, String blood, String email) {
        super(first,last,gender,phone,email);
        this.bloodGroup=blood;
    }


    public void setPatientId(String patientId) {
        if (HelperUtils.isValidString(patientId, 5, 20)) {
            this.patientId = patientId;
        } else {
            System.err.println("Validation Error: Invalid patient ID format.");
        }
    }

    public void setBloodGroup(String bloodGroup) {
        String bgRegex = "^(A|B|AB|O)[+-]$";
        if (HelperUtils.isValidString(bloodGroup, bgRegex)) {
            this.bloodGroup = bloodGroup.toUpperCase();
        } else {
            System.err.println("Validation Error: Invalid blood group format. Please use format like 'A+' or 'O-'.");
        }
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = (allergies != null) ? allergies : new ArrayList<>();
    }

    public void setEmergencyContact(String emergencyContact) {
        if (HelperUtils.isValidString(emergencyContact, 8, 15)) {
            this.emergencyContact = emergencyContact;
        } else {
            System.err.println("Validation Error: Invalid emergency contact phone number.");
        }
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        if (HelperUtils.isValidDate(registrationDate) && !HelperUtils.isFutureDate(registrationDate)) {
            this.registrationDate = registrationDate;
        } else {
            System.err.println("Validation Error: Registration date cannot be a future date.");
        }
    }

    public void setInsuranceId(String insuranceId) {
        if (HelperUtils.isValidString(insuranceId, 5, 50)) {
            this.insuranceId = insuranceId;
        } else {
            System.err.println("Validation Error: Invalid insurance ID provided.");
        }
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = (medicalRecords != null) ? medicalRecords : new ArrayList<>();    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = (appointments != null) ? appointments : new ArrayList<>();    }

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
        System.out.println("Patient: " + getFirstName() + " " + getLastName() +
                " | ID: " + patientId + " | Blood: " + bloodGroup);
    }


    public void addMedicalRecord(MedicalRecord record) {
        if (HelperUtils.isNotNull(record)) {
            medicalRecords.add(record);
            System.out.println("Medical record added for patient " + getFirstName());
        } else {
            System.err.println("Cannot add a null medical record.");
        }
    }

    public void addAppointment(Appointment appointment) {
        if (HelperUtils.isNotNull(appointment)) {
            appointments.add(appointment);
            System.out.println("Appointment added for patient " + getFirstName());
        } else {
            System.err.println("Cannot add a null appointment.");
        }
    }

    public boolean updateInsurance(String newInsuranceId) {
        if (HelperUtils.isValidString(newInsuranceId, 5, 50)) {
            this.insuranceId = newInsuranceId;
            System.out.println("Insurance ID updated for patient " + getFirstName() + " to: " + newInsuranceId);
            return true;
        } else {
            System.err.println("Failed to update insurance. Invalid ID provided.");
            return false;
        }
    }

    public void updateContact(String phone) {
        setPhoneNumber(phone);
        System.out.println("Updated phone number for patient " + getFirstName() + " to: " + phone);
    }

    public void updateContact(String phone, String email) {
        setPhoneNumber(phone);
        setEmail(email);
        System.out.println("Updated contact for patient " + getFirstName() + ": " +
                "New Phone: " + phone + ", New Email: " + email);
    }

    public void updateContact(String phone, String email, String address) {
        setPhoneNumber(phone);
        setEmail(email);
        setAddress(address);
        System.out.println("Updated for patient " + getFirstName() + "new phone" + phone +
                "new email: " + email + "new address: " + address);
    }


    public void addAllergy(String allergy) {
        if (HelperUtils.isValidString(allergy)) {
            allergies.add(allergy.trim());
            System.out.println("Added allergy: " + allergy);
        } else {
            System.err.println("Cannot add null or empty allergy.");
        }
    }

    public void addAllergy(List<String> newAllergies) {
        if (HelperUtils.isNotNull(newAllergies)) {
            for (String allergy : newAllergies) {
                addAllergy(allergy);
            }
        }
    }
}