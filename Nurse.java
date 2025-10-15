package Entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Stack;

public class Nurse extends Person {
    private String nurseId;
    private String departmentIdl;
    private String shift;
    private String qualification;
    private List <Patient> assignedPatients;

    public Nurse(String id, String firstName, String lastName, LocalDate dateOfBirth,
                 String gender, String phoneNumber, String address, String email,
                 String nurseId, String departmentIdl, String shift,
                 String qualification, List<Patient> assignedPatients) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email);
        this.nurseId = nurseId;
        this.departmentIdl = departmentIdl;
        this.shift = shift;
        this.qualification = qualification;
        this.assignedPatients = assignedPatients;
    }

    public Nurse(String nurseId, String departmentIdl, String shift,
                 String qualification, List<Patient> assignedPatients) {
        this.nurseId = nurseId;
        this.departmentIdl = departmentIdl;
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

    public String getDepartmentIdl() {
        return departmentIdl;
    }

    public void setDepartmentIdl(String departmentIdl) {
        this.departmentIdl = departmentIdl;
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
        System.out.println("departmentIdl:           " + departmentIdl);
        System.out.println("shift:           " + shift);
        System.out.println("qualification:           " + qualification);
        System.out.println("assignedPatients:           " + assignedPatients);


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

}
