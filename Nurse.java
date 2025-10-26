package Entity;

import Interface.Displayable;

import java.time.LocalDate;
import java.util.List;

public class Nurse extends Person implements Displayable {
    private String nurseId;
    private String departmentId;
    private String shift;
    private String qualification;
    private List<Patient> assignedPatients;

    public Nurse() {
    }

    public Nurse(String id, String firstName, String lastName, LocalDate dateOfBirth,
                 String gender, String phoneNumber, String address, String email,
                 String nurseId, String departmentId, String shift,
                 String qualification, List<Patient> assignedPatients) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email);
        this.nurseId = nurseId;
        this.departmentId = departmentId;
        this.shift = shift;
        this.qualification = qualification;
        this.assignedPatients = assignedPatients;
    }

    public Nurse(String nurseId, String departmentId, String shift,
                 String qualification, List<Patient> assignedPatients) {
        this.nurseId = nurseId;
        this.departmentId = departmentId;
        this.shift = shift;
        this.qualification = qualification;
        this.assignedPatients = assignedPatients;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public List<Patient> getAssignedPatients() {
        return assignedPatients;
    }

    public void setAssignedPatients(List<Patient> assignedPatients) {
        this.assignedPatients = assignedPatients;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("nurseId:           " + nurseId);
        System.out.println("departmentIdl:           " + departmentId);
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
        if (patient != null && !assignedPatients.contains(patient)) {
            assignedPatients.add(patient);
            System.out.println("Assigned patient " + patient.getFirstName() +
                    " to Nurse " + getFirstName());
        }
    }

    public void removePatient(String patientId) {
        assignedPatients.removeIf(p -> p.getPatientId().equals(patientId));
        System.out.println("Removed patient " + patientId + " from Nurse " + getFirstName());
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
