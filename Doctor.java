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

    
}
