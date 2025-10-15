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

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("doctorId:           " + doctorId);
        System.out.println("specialization:           " + specialization);
        System.out.println("qualification:           " + qualification);
        System.out.println("experienceYears:           " + experienceYears);
        System.out.println("departmentId:           " + departmentId);
        System.out.println("consultationFee:           " + consultationFee);
        System.out.println("availableSlots:           " + availableSlots);
        System.out.println("assignedPatients:           " + assignedPatients);

    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId='" + doctorId + '\'' +
                ", specialization='" + specialization + '\'' +
                ", qualification='" + qualification + '\'' +
                ", experienceYears=" + experienceYears +
                ", departmentId='" + departmentId + '\'' +
                ", consultationFee=" + consultationFee +
                '}';
    }

    public static void assignPatient(Patient patient){
        if (patient != null && !assignedPatients.contains(patient)){
            assignedPatients.add(patient);
            System.out.println("add");
        }

    }

    public static void removePatient(String patientId){
        if (assignedPatients == null || assignedPatients.isEmpty()) return;
        assignedPatients.removeIf(p -> p.getPatientId().equals(patientId));
        System.out.println(" removed");
    }

    public void updateAvailability(List<String> newSlots) {
        this.availableSlots = newSlots;
        System.out.println("Updated available slots for Dr. " + getFirstName());

    }
}
