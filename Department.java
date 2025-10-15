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

    public Department(String departmentId, String departmentName, String headDoctorId,
                      List<Doctor> doctors, List<Nurse> nurses, Integer bedCapacity,
                      Integer availableBeds) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.headDoctorId = headDoctorId;
        this.doctors = doctors;
        this.nurses = nurses;
        this.bedCapacity = bedCapacity;
        this.availableBeds = availableBeds;
    }

    public Department() {
    }
}
