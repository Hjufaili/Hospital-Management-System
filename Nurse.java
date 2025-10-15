package Entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Stack;

public class Nurse extends Person {
    private String nurseId;
    private String departmentIdl;
    private String shift;
    private String qualification;
    private List <String> assignedPatients;

    public Nurse(String id, String firstName, String lastName, LocalDate dateOfBirth,
                 String gender, String phoneNumber, String address, String email,
                 String nurseId, String departmentIdl, String shift,
                 String qualification, List<String> assignedPatients) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email);
        this.nurseId = nurseId;
        this.departmentIdl = departmentIdl;
        this.shift = shift;
        this.qualification = qualification;
        this.assignedPatients = assignedPatients;
    }

    public Nurse(String nurseId, String departmentIdl, String shift,
                 String qualification, List<String> assignedPatients) {
        this.nurseId = nurseId;
        this.departmentIdl = departmentIdl;
        this.shift = shift;
        this.qualification = qualification;
        this.assignedPatients = assignedPatients;
    }
}
