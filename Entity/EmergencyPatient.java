package Entity;

import Interface.Displayable;

import java.time.LocalDate;
import java.util.List;

public class EmergencyPatient extends InPatient implements Displayable {
    private String emergencyType;
    private String arrivalMode;
    private Integer triageLevel;
    private Boolean admittedViaER;

    // Default values
    private static final Double DEFAULT_DAILY_CHARGE = 500.0;
    private static final String DEFAULT_ROOM = "ER_BAY";
    private static final String DEFAULT_ADMIT_DOC = "DOC999"; // ER Doctor


    public EmergencyPatient(String firstName, String lastName, String gender, String phoneNumber,
                            String bloodGroup, String emergencyType, String arrivalMode,
                            Integer triageLevel) {

        super(
                firstName, lastName, gender, phoneNumber,
                bloodGroup, DEFAULT_ADMIT_DOC, DEFAULT_ROOM, DEFAULT_DAILY_CHARGE
        );

        this.emergencyType = emergencyType;
        this.arrivalMode = arrivalMode;
        this.triageLevel = triageLevel;
        this.admittedViaER = true;

        setAdmissionDate(LocalDate.now());
        setBedNumber(DEFAULT_ROOM + "-T" + triageLevel);
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

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Emergency Type: " + emergencyType);
        System.out.println("Arrival Mode: " + arrivalMode);
        System.out.println("Triage Level: " + triageLevel);
        System.out.println("Admitted via ER: " + (admittedViaER ? "Yes" : "No"));
    }

    @Override
    public void displaySummary() {
        System.out.println("Patient. " + getFirstName() + " " + getLastName() +
                " | " + emergencyType + " | " + arrivalMode + " yrs");

    }
}
