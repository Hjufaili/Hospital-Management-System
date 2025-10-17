package Entity;

import java.time.LocalDate;
import java.util.List;

public class InPatient extends Patient{

    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String roomNumber;
    private String bedNumber;
    private String admittingDoctorId;
    private Double dailyCharges;

    public InPatient(LocalDate admissionDate, LocalDate dischargeDate, String roomNumber, String bedNumber,
                     String admittingDoctorId, Double dailyCharges) {
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
        this.admittingDoctorId = admittingDoctorId;
        this.dailyCharges = dailyCharges;
    }

    public InPatient(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender,
                     String phoneNumber, String address, String email, String patientId,
                     String bloodGroup, List<String> allergies, String emergencyContact,
                     LocalDate registrationDate, String insuranceId, List<MedicalRecord> medicalRecords,
                     List<Appointment> appointments, LocalDate admissionDate, LocalDate dischargeDate,
                     String roomNumber, String bedNumber, String admittingDoctorId, Double dailyCharges) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email,
                patientId, bloodGroup, allergies, emergencyContact, registrationDate, insuranceId,
                medicalRecords, appointments);
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
        this.admittingDoctorId = admittingDoctorId;
        this.dailyCharges = dailyCharges;
    }

    public InPatient(String patientId, String bloodGroup, List<String> allergies,
                     String emergencyContact, LocalDate registrationDate, String insuranceId,
                     List<MedicalRecord> medicalRecords, List<Appointment> appointments,
                     LocalDate admissionDate, LocalDate dischargeDate, String roomNumber,
                     String bedNumber, String admittingDoctorId, Double dailyCharges) {
        super(patientId, bloodGroup, allergies, emergencyContact, registrationDate,
                insuranceId, medicalRecords, appointments);
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
        this.admittingDoctorId = admittingDoctorId;
        this.dailyCharges = dailyCharges;
    }
}
