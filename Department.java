package Entity;

import java.util.List;

public class Department {
    private String departmentId;
    private String departmentName;
    private String headDoctorId;
    private List<Doctor> doctors;
    private List<Nurse> nurses;
    private Integer bedCapacity;
    private Integer availableBeds;
    
}
