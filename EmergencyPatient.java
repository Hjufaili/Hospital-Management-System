package Entity;

import java.time.LocalDate;
import java.util.List;

public class EmergencyPatient extends InPatient{
    private String emergencyType;
    private String arrivalMode;
    private Integer triageLevel;
    private Boolean admittedViaER;

    public EmergencyPatient(LocalDate admissionDate, LocalDate dischargeDate, String roomNumber,
                            String bedNumber, String admittingDoctorId, Double dailyCharges,
                            String emergencyType, String arrivalMode, Integer triageLevel,
                            Boolean admittedViaER) {
        super(admissionDate, dischargeDate, roomNumber, bedNumber, admittingDoctorId, dailyCharges);
        this.emergencyType = emergencyType;
        this.arrivalMode = arrivalMode;
        this.triageLevel = triageLevel;
        this.admittedViaER = admittedViaER;
    }

    public EmergencyPatient(String id, String firstName, String lastName, LocalDate dateOfBirth,
                            String gender, String phoneNumber, String address, String email,
                            String patientId, String bloodGroup, List<String> allergies,
                            String emergencyContact, LocalDate registrationDate, String insuranceId,
                            List<MedicalRecord> medicalRecords, List<Appointment> appointments,
                            LocalDate admissionDate, LocalDate dischargeDate, String roomNumber,
                            String bedNumber, String admittingDoctorId, Double dailyCharges,
                            String emergencyType, String arrivalMode, Integer triageLevel,
                            Boolean admittedViaER) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email,
                patientId, bloodGroup, allergies, emergencyContact, registrationDate, insuranceId,
                medicalRecords, appointments, admissionDate, dischargeDate, roomNumber,
                bedNumber, admittingDoctorId, dailyCharges);
        this.emergencyType = emergencyType;
        this.arrivalMode = arrivalMode;
        this.triageLevel = triageLevel;
        this.admittedViaER = admittedViaER;
    }

    public EmergencyPatient(String patientId, String bloodGroup, List<String> allergies,
                            String emergencyContact, LocalDate registrationDate, String insuranceId,
                            List<MedicalRecord> medicalRecords, List<Appointment> appointments,
                            LocalDate admissionDate, LocalDate dischargeDate, String roomNumber,
                            String bedNumber, String admittingDoctorId, Double dailyCharges,
                            String emergencyType, String arrivalMode, Integer triageLevel, Boolean admittedViaER) {
        super(patientId, bloodGroup, allergies, emergencyContact, registrationDate, insuranceId,
                medicalRecords, appointments, admissionDate, dischargeDate, roomNumber,
                bedNumber, admittingDoctorId, dailyCharges);
        this.emergencyType = emergencyType;
        this.arrivalMode = arrivalMode;
        this.triageLevel = triageLevel;
        this.admittedViaER = admittedViaER;
    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(String emergencyType) {
        this.emergencyType = emergencyType;
    }

    public String getArrivalMode() {
        return arrivalMode;
    }

    public void setArrivalMode(String arrivalMode) {
        this.arrivalMode = arrivalMode;
    }

    public Integer getTriageLevel() {
        return triageLevel;
    }

    public void setTriageLevel(Integer triageLevel) {
        this.triageLevel = triageLevel;
    }

    public Boolean getAdmittedViaER() {
        return admittedViaER;
    }

    public void setAdmittedViaER(Boolean admittedViaER) {
        this.admittedViaER = admittedViaER;
    }
}
