package Entity;

import java.time.LocalDate;
import java.util.List;



public class Doctor extends Person {
    private String doctorId;
    private String specialization ;
    private String qualification;
    private Integer experienceYears;
    private String departmentId ;
    private Double consultationFee ;
    private static List<String> availableSlots;
    private static List<Patient> assignedPatients;

    public Doctor(String id, String firstName, String lastName, LocalDate dateOfBirth,
                  String gender, String phoneNumber, String address, String email,
                  String doctorId, String specialization, String qualification,
                  Integer experienceYears, String departmentId, Double consultationFee,
                  List<String> availableSlots, List<Patient> assignedPatients) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email);
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.qualification = qualification;
        this.experienceYears = experienceYears;
        this.departmentId = departmentId;
        this.consultationFee = consultationFee;
        this.availableSlots = availableSlots;
        this.assignedPatients = assignedPatients;
    }

    public Doctor(String doctorId, String specialization, String qualification,
                  Integer experienceYears, String departmentId, Double consultationFee,
                  List<String> availableSlots, List<Patient> assignedPatients) {
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.qualification = qualification;
        this.experienceYears = experienceYears;
        this.departmentId = departmentId;
        this.consultationFee = consultationFee;
        this.availableSlots = availableSlots;
        this.assignedPatients = assignedPatients;
    }
}
