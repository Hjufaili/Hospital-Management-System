package Entity;

import Interface.Displayable;

import java.time.LocalDate;
import java.util.List;

public class Surgeon extends Doctor implements Displayable {

    private static Integer surgeriesPerformed;
    private static List<String> surgeryTypes;
    private Boolean operationTheatreAccess;

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

    public static void performSurgery(String surgeryType){
        surgeriesPerformed++;
        if (!surgeryTypes.contains(surgeryType)) {
            surgeryTypes.add(surgeryType);
        }
        System.out.println("Dr. " + getFirstName() + " performed a " + surgeryType + " surgery. " +
                "Total surgeries: " + surgeriesPerformed);
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
}
