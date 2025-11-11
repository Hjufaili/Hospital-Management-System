package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Surgeon extends Doctor implements Displayable {

    private  Integer surgeriesPerformed;
    private  List<String> surgeryTypes;
    private Boolean operationTheatreAccess;

    private static final Double DEFAULT_CONSULTATION_FEE = 250.0;
    private static final Boolean DEFAULT_OT_ACCESS = true;

    public Surgeon(Integer surgeriesPerformed, List<String> surgeryTypes,
                   Boolean operationTheatreAccess) {
        this.surgeriesPerformed = surgeriesPerformed;
        this.surgeryTypes = surgeryTypes;
        this.operationTheatreAccess = operationTheatreAccess;
    }

    public Surgeon(String id, String firstName, String lastName, LocalDate dateOfBirth,
                   String gender, String phoneNumber, String address, String email,
                   String doctorId, String specialization, String qualification,
                   Integer experienceYears, String departmentId, Double consultationFee,
                   List<String> availableSlots, List<Patient> assignedPatients,
                   Integer surgeriesPerformed, List<String> surgeryTypes, Boolean operationTheatreAccess) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email, doctorId,
                specialization, qualification, experienceYears, departmentId, consultationFee,
                availableSlots, assignedPatients);
        this.surgeriesPerformed = surgeriesPerformed;
        this.surgeryTypes = surgeryTypes;
        this.operationTheatreAccess = operationTheatreAccess;
    }

    public Surgeon(String firstName, String lastName, String specialization, String departmentId) {


        super(
                HelperUtils.generateId("PER"), firstName, lastName, (LocalDate) null,
                "N/A", "N/A", "N/A", "N/A",

                HelperUtils.generateId("DOC"), specialization, "N/A", 0, departmentId,
                DEFAULT_CONSULTATION_FEE, new ArrayList<String>(), new ArrayList<Patient>()
        );

        this.surgeriesPerformed = 0;
        this.surgeryTypes = new ArrayList<>();
        this.operationTheatreAccess = DEFAULT_OT_ACCESS;
    }

    public Surgeon(String doctorId, String specialization, String qualification,
                   Integer experienceYears, String departmentId, Double consultationFee,
                   List<String> availableSlots, List<Patient> assignedPatients,
                   Integer surgeriesPerformed, List<String> surgeryTypes,
                   Boolean operationTheatreAccess) {
        super(doctorId, specialization, qualification, experienceYears, departmentId,
                consultationFee, availableSlots, assignedPatients);
        this.surgeriesPerformed = surgeriesPerformed;
        this.surgeryTypes = surgeryTypes;
        this.operationTheatreAccess = operationTheatreAccess;
    }

    public Integer getSurgeriesPerformed() {
        return surgeriesPerformed;
    }

    public void setSurgeriesPerformed(Integer surgeriesPerformed) {
        this.surgeriesPerformed = surgeriesPerformed;
    }

    public List<String> getSurgeryTypes() {
        return surgeryTypes;
    }

    public void setSurgeryTypes(List<String> surgeryTypes) {
        this.surgeryTypes = surgeryTypes;
    }

    public Boolean getOperationTheatreAccess() {
        return operationTheatreAccess;
    }

    public void setOperationTheatreAccess(Boolean operationTheatreAccess) {
        this.operationTheatreAccess = operationTheatreAccess;
    }

    public  void performSurgery(String surgeryType){
        if (!operationTheatreAccess) {
            System.out.println("Dr. " + getFirstName() + " does not have Operation Theatre access to perform surgery.");
            return;
        }

        // Safety check for null initialization
        if (this.surgeriesPerformed == null) this.surgeriesPerformed = 0;
        if (this.surgeryTypes == null) this.surgeryTypes = new ArrayList<>();

        this.surgeriesPerformed++;
        if (!this.surgeryTypes.contains(surgeryType)) {
            this.surgeryTypes.add(surgeryType);
        }
        System.out.println("Dr. " + getFirstName() + " performed a " + surgeryType + " surgery. " +
                "Total surgeries: " + this.surgeriesPerformed);
    }

    public void updateSurgeryCount(int newCount){
        if (newCount < 0) {
            System.out.println("Surgery count cannot be negative!");
            return;
        }
        this.surgeriesPerformed = newCount;
        System.out.println("Dr. " + getFirstName() +
                "'s surgery count updated to: " + surgeriesPerformed);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Surgeries Performed: " + surgeriesPerformed);
        System.out.println("Surgery Types: " + surgeryTypes);
        System.out.println("Operation Theatre Access: " + (operationTheatreAccess ? "Yes" : "No"));
    }

    @Override
    public void displaySummary() {
        super.displaySummary();
        System.out.println("Type: Surgeon | Surgeries: " + surgeriesPerformed + " | OT Access: " + (operationTheatreAccess ? "Yes" : "No"));
    }
}
