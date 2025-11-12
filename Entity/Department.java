package Entity;

import Interface.Displayable;
import Utils.HelperUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@Getter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true, exclude = {"doctors","nurses"})
public class Department implements Displayable {
    private String departmentId=HelperUtils.generateId("DPT");
    private String departmentName;
    private String headDoctorId;
    private List<Doctor> doctors = new ArrayList<>();
    private List<Nurse> nurses = new ArrayList<>();
    private Integer bedCapacity = 0;
    private Integer availableBeds = 0;

//    public Department(String departmentId, String departmentName, String headDoctorId,
//                      List<Doctor> doctors, List<Nurse> nurses, Integer bedCapacity,
//                      Integer availableBeds) {
//        setDepartmentId(departmentId);
//        setDepartmentName(departmentName);
//        setHeadDoctorId(headDoctorId);
//        setBedCapacity(bedCapacity);
//        setAvailableBeds(availableBeds);
//        setDoctors(doctors);
//        setNurses(nurses);
//    }
//
//    public Department(String departmentName, String headDoctorId, int bedCapacity) {
//        this.departmentId = HelperUtils.generateId("DPT");
//        setDepartmentName(departmentName);
//        setHeadDoctorId(headDoctorId);
//        setBedCapacity(bedCapacity); // Also sets availableBeds initially
//
//        this.doctors = new ArrayList<>();
//        this.nurses = new ArrayList<>();
//    }
//
//    public Department(String departmentId, String departmentName,
//                      String headDoctorId, int bedCapacity) {
//        this.departmentId = departmentId;
//        this.departmentName = departmentName;
//        this.headDoctorId = headDoctorId;
//        this.bedCapacity = bedCapacity;
//        this.availableBeds = bedCapacity; // initially all beds available
//        this.doctors = new ArrayList<>();
//        this.nurses = new ArrayList<>();
//    }
//
//    public Department() {
//        this.departmentId = HelperUtils.generateId("DPT");
//        this.doctors = new ArrayList<>();
//        this.nurses = new ArrayList<>();
//        this.bedCapacity = 0;
//        this.availableBeds = 0;
//    }


    public void setDepartmentId(String departmentId) {
        if (HelperUtils.isValidString(departmentId, 3, 20)) {
            this.departmentId = departmentId;
        } else {
            System.err.println("Validation Error: Invalid Department ID.");
        }
    }

    public void setDepartmentName(String departmentName) {
        if (HelperUtils.isValidString(departmentName, 5, 50)) {
            this.departmentName = departmentName;
        } else {
            System.err.println("Validation Error: Invalid Department Name.");
        }
    }

    public void setHeadDoctorId(String headDoctorId) {
        if (HelperUtils.isValidString(headDoctorId, 5, 20)) {
            this.headDoctorId = headDoctorId;
        } else {
            System.err.println("Validation Error: Invalid Head Doctor ID.");
        }
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = (doctors != null) ? doctors : new ArrayList<>();
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = (nurses != null) ? nurses : new ArrayList<>();
    }

    public void setBedCapacity(Integer bedCapacity) {
        if (bedCapacity != null && HelperUtils.isPositive(bedCapacity)) {
            this.bedCapacity = bedCapacity;
            if (availableBeds == null || availableBeds > bedCapacity) {
                this.availableBeds = bedCapacity;
            }
        } else {
            System.err.println("Validation Error: Bed capacity must be a positive integer.");
        }
    }

    public void setAvailableBeds(Integer availableBeds) {
        if (availableBeds != null && availableBeds >= 0 && availableBeds <= this.bedCapacity) {
            this.availableBeds = availableBeds;
        } else if (availableBeds != null && availableBeds > this.bedCapacity) {
            System.err.println("Validation Error: Available beds cannot exceed bed capacity (" + this.bedCapacity + ").");
        } else {
            System.err.println("Validation Error: Available beds must be a non-negative integer.");
        }
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

//    @Override
//    public String toString() {
//        return "Department{" +
//                "departmentId='" + departmentId + '\'' +
//                ", departmentName='" + departmentName + '\'' +
//                ", headDoctorId='" + headDoctorId + '\'' +
//                ", doctors=" + doctors +
//                ", nurses=" + nurses +
//                ", bedCapacity=" + bedCapacity +
//                ", availableBeds=" + availableBeds +
//                '}';
//    }

    public void assignDoctor(Doctor doctor) {
        if (HelperUtils.isNotNull(doctor) && !doctors.contains(doctor)) {
            doctors.add(doctor);
            System.out.println("Doctor " + doctor.getLastName() + " assigned to " + departmentName);
        } else if (doctors.contains(doctor)) {
            System.err.println("Doctor " + doctor.getLastName() + " is already in this department.");
        } else {
            System.err.println("Cannot assign a null doctor.");
        }
    }

    public void assignDoctor(List<Doctor> newDoctors) {
        if (HelperUtils.isNotNull(newDoctors)) {
            for (Doctor doctor : newDoctors) {
                assignDoctor(doctor);
            }
        }
    }

    public void assignNurse(Nurse nurse) {
        if (HelperUtils.isNotNull(nurse) && !nurses.contains(nurse)) {
            nurses.add(nurse);
            System.out.println("Nurse " + nurse.getLastName() + " assigned to " + departmentName);
        } else if (nurses.contains(nurse)) {
            System.err.println("Nurse " + nurse.getLastName() + " is already in this department.");
        } else {
            System.err.println("Cannot assign a null nurse.");
        }
    }

    public void assignNurse(List<Nurse> newNurses) {
        if (HelperUtils.isNotNull(newNurses)) {
            for (Nurse nurse : newNurses) {
                assignNurse(nurse); // Delegate to single method for validation
            }
        }
    }

    public void updateBedAvailability(int occupiedBeds) {
        int newAvailable = bedCapacity - occupiedBeds;
        if (newAvailable >= 0) {
            setAvailableBeds(newAvailable); // Use setter for validation
            System.out.println("Updated bed availability for " + departmentName +
                    ": " + availableBeds + "/" + bedCapacity);
        } else {
            System.err.println("Error: Occupied beds (" + occupiedBeds +
                    ") exceeds bed capacity (" + bedCapacity + ").");
        }
    }
}
