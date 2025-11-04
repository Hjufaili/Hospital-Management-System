package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Nurse extends Person implements Displayable {
    private String nurseId;
    private String departmentId;
    private String shift;
    private String qualification;
    private List<Patient> assignedPatients;

    public Nurse() {
        super();
        this.nurseId = HelperUtils.generateId("NUR");
        this.assignedPatients = new ArrayList<>();
    }

    public Nurse(String id, String firstName, String lastName, LocalDate dateOfBirth,
                 String gender, String phoneNumber, String address, String email,
                 String nurseId, String departmentId, String shift,
                 String qualification, List<Patient> assignedPatients) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email);
        setNurseId(nurseId);
        setDepartmentId(departmentId);
        setShift(shift);
        setQualification(qualification);
        setAssignedPatients(assignedPatients);
    }

    public Nurse(String nurseId, String departmentId, String shift,
                 String qualification, List<Patient> assignedPatients) {
        super();
        this.nurseId = nurseId;
        this.departmentId = departmentId;
        this.shift = shift;
        this.qualification = qualification;
        this.assignedPatients = assignedPatients;
    }

    public Nurse(String firstName, String lastName, LocalDate dateOfBirth,
                 String gender, String phoneNumber, String address, String email,
                 String departmentId, String shift, String qualification) {

        super(HelperUtils.generateId("PER"), firstName, lastName, dateOfBirth, gender, phoneNumber, email, address);

        this.nurseId = HelperUtils.generateId("NUR");
        setDepartmentId(departmentId);
        setShift(shift);
        setQualification(qualification);
        this.assignedPatients = new ArrayList<>();
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        if (HelperUtils.isValidString(nurseId, 5, 20)) { // Example constraints
            this.nurseId = nurseId;
        } else {
            System.err.println("Validation Error: Invalid Nurse ID provided.");
        }
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        if (HelperUtils.isValidString(departmentId, 5, 20)) {
            this.departmentId = departmentId;
        } else {
            System.err.println("Validation Error: Invalid department ID value provided.");
        }
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        if (Utils.HelperUtils.isValidString(shift)) { // Check for not null and not empty
            this.shift = shift;
        } else {
            System.err.println("Invalid shift value provided.");
        }
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        if (HelperUtils.isValidString(qualification, 3)) {
            this.qualification = qualification;
        } else {
            System.err.println("Validation Error: Invalid qualification provided.");
        }
    }

    public List<Patient> getAssignedPatients() {
        return assignedPatients;
    }

    public void setAssignedPatients(List<Patient> assignedPatients) {
        this.assignedPatients = (assignedPatients != null) ? assignedPatients : new ArrayList<>();
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("nurseId:           " + nurseId);
        System.out.println("departmentId:           " + departmentId);
        System.out.println("shift:           " + shift);
        System.out.println("qualification:           " + qualification);
        System.out.println("assignedPatients:           " + assignedPatients);
        if (assignedPatients == null || assignedPatients.isEmpty()) {
            System.out.println("  None assigned currently.");
        } else {
            for (Patient p : assignedPatients) {
                System.out.println("  - " + p.getPatientId() + " | " + p.getFirstName() + " " + p.getLastName());
            }
        }
        System.out.println("------------------------------------");

    }

    @Override
    public void displaySummary() {
        System.out.println("Nurse " + getFirstName() + " " + getLastName() +
                " | Dept: " + departmentId + " | Shift: " + shift);
    }

    public void assignPatient(Patient patient) {
        if (HelperUtils.isNotNull(patient) && !assignedPatients.contains(patient)) {
            assignedPatients.add(patient);
            System.out.println("Assigned patient " + patient.getFirstName() + " (" + patient.getPatientId() + ") to Nurse " + getFirstName());
        } else if (assignedPatients.contains(patient)) {
            System.err.println("Patient " + patient.getPatientId() + " is already assigned to this nurse.");
        } else {
            System.err.println("Cannot assign a null patient.");
        }
    }

    public void assignPatient(List<Patient> newPatients) {
        if (HelperUtils.isNotNull(newPatients)) {
            for (Patient patient : newPatients) {
                assignPatient(patient);
            }
            System.out.println("Attempted to assign " + newPatients.size() + " patients.");
        }
    }

    public void removePatient(String patientId) {
        boolean removed = assignedPatients.removeIf(p -> HelperUtils.isNotNull(p) && Objects.equals(p.getPatientId(), patientId));
        if (removed) {
            System.out.println("Removed patient " + patientId + " from Nurse " + getFirstName());
        } else {
            System.err.println("Patient with ID " + patientId + " was not found assigned to Nurse " + getFirstName());
        }
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "nurseId='" + nurseId + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", shift='" + shift + '\'' +
                ", qualification='" + qualification + '\'' +
                ", assignedPatients=" + (assignedPatients != null ? assignedPatients.size() : 0) +
                '}';
    }


}
