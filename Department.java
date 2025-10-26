package Entity;

import Interface.Displayable;

import java.util.ArrayList;
import java.util.List;

public class Department implements Displayable {
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

    public Department(String departmentId, String departmentName,
                      String headDoctorId, int bedCapacity) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.headDoctorId = headDoctorId;
        this.bedCapacity = bedCapacity;
        this.availableBeds = bedCapacity; // initially all beds available
        this.doctors = new ArrayList<>();
        this.nurses = new ArrayList<>();
    }

    public Department() {
    }


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHeadDoctorId() {
        return headDoctorId;
    }

    public void setHeadDoctorId(String headDoctorId) {
        this.headDoctorId = headDoctorId;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

    public Integer getBedCapacity() {
        return bedCapacity;
    }

    public void setBedCapacity(Integer bedCapacity) {
        this.bedCapacity = bedCapacity;
    }

    public Integer getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(Integer availableBeds) {
        this.availableBeds = availableBeds;
    }

    public void displayInfo() {
        System.out.println("departmentId:     " + departmentId);
        System.out.println("departmentName:     " + departmentName);
        System.out.println("headDoctorId:     " + headDoctorId);
        System.out.println("doctors:     " + doctors);
        System.out.println("nurses:     " + nurses);
        System.out.println("bedCapacity:     " + bedCapacity);
        System.out.println("availableBeds:     " + availableBeds);
    }

    @Override
    public void displaySummary() {
        System.out.println("Department: " + departmentName + " (" + departmentId + ") | Head Doctor: " + headDoctorId);


    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", headDoctorId='" + headDoctorId + '\'' +
                ", doctors=" + doctors +
                ", nurses=" + nurses +
                ", bedCapacity=" + bedCapacity +
                ", availableBeds=" + availableBeds +
                '}';
    }

    public void assignDoctor(Doctor doctor) {
        if (doctor != null && !doctors.contains(doctor)) {
            doctors.add(doctor);
            System.out.println("Doctor " + doctor.getFirstName() +
                    " assigned to department " + departmentName);
        }
    }

    public void assignNurse(Nurse nurse) {
        if (nurse != null && !nurses.contains(nurse)) {
            nurses.add(nurse);
            System.out.println("Nurse " + nurse.getFirstName() +
                    " assigned to department " + departmentName);
        }
    }

    public void updateBedAvailability(int occupiedBeds) {
        int newAvailable = bedCapacity - occupiedBeds;
        this.availableBeds = Math.max(0, newAvailable);
        System.out.println("Updated bed availability for " + departmentName +
                ": " + availableBeds + "/" + bedCapacity);
    }
}
