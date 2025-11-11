package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Doctor extends Person implements Displayable {
    private String doctorId;
    private String specialization;
    private String qualification;
    private Integer experienceYears;
    private String departmentId;
    private Double consultationFee;
    private List<String> availableSlots;
    private List<Patient> assignedPatients;

    public Doctor() {
        super();
        this.doctorId = HelperUtils.generateId("DOC");
        this.availableSlots = new ArrayList<>();
        this.assignedPatients = new ArrayList<>();
    }

    public Doctor(String id, String firstName, String lastName, LocalDate dateOfBirth,
                  String gender, String phoneNumber, String email, String address,
                  String doctorId, String specialization, String qualification,
                  Integer experienceYears, String departmentId, Double consultationFee,
                  List<String> availableSlots, List<Patient> assignedPatients) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address);

        setDoctorId(doctorId);
        setSpecialization(specialization);
        setQualification(qualification);
        setExperienceYears(experienceYears);
        setDepartmentId(departmentId);
        setConsultationFee(consultationFee);

        setAvailableSlots(availableSlots);
        setAssignedPatients(assignedPatients);
    }

    public Doctor(String doctorId, String specialization, String qualification,
                  Integer experienceYears, String departmentId, Double consultationFee,
                  List<String> availableSlots, List<Patient> assignedPatients) {

        this.doctorId = HelperUtils.generateId("DOC");
        this.specialization = specialization;
        this.qualification = qualification;
        this.experienceYears = experienceYears;
        this.departmentId = departmentId;
        this.consultationFee = consultationFee;
        this.availableSlots = availableSlots;
        this.assignedPatients = assignedPatients;
    }

    public Doctor(String firstName, String lastName, LocalDate dateOfBirth,
                  String gender, String phoneNumber, String email, String address,
                  String specialization, String qualification,
                  Integer experienceYears, String departmentId, Double consultationFee) {

        super(HelperUtils.generateId("PER"), firstName, lastName, dateOfBirth, gender, phoneNumber, email, address);

        this.doctorId = HelperUtils.generateId("DOC");

        // Use setters for validation
        setSpecialization(specialization);
        setQualification(qualification);
        setExperienceYears(experienceYears);
        setDepartmentId(departmentId);
        setConsultationFee(consultationFee);

        this.availableSlots = new ArrayList<>();
        this.assignedPatients = new ArrayList<>();
    }

    public Doctor(String first, String last, String spec, String qual, int years, String depId) {
        setFirstName(first);
        setLastName(last);
        setSpecialization(spec);
        setQualification(qual);
        setExperienceYears(years);
        setDepartmentId(depId);
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
    public void displaySummary() {
        System.out.println("Dr. " + getFirstName() + " " + getLastName() +
                " | " + specialization + " | " + experienceYears + " yrs");
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

    public void assignPatient(Patient patient) {
        if (HelperUtils.isNotNull(patient) && !assignedPatients.contains(patient)) {
            assignedPatients.add(patient);
            System.out.println("Assigned patient " + patient.getFirstName() + " to Dr. " + getFirstName());
        } else if (HelperUtils.isNotNull(patient)) {
            System.err.println("Patient " + patient.getPatientId() + " is already assigned to this doctor.");
        } else {
            System.err.println("Cannot assign a null patient.");
        }
    }


    public void assignPatient(List<Patient> newPatients) {
        if (HelperUtils.isNotNull(newPatients)) {
            for (Patient patient : newPatients) {
                assignPatient(patient);
            }
        }
    }

    public void removePatient(String patientId) {
        if (assignedPatients == null) return;
        boolean removed = assignedPatients.removeIf(p -> Objects.equals(p.getPatientId(), patientId));

        if (removed) {
            System.out.println("Removed patient " + patientId + " from Dr. " + getFirstName());
        } else {
            System.err.println("Patient with ID " + patientId + " not found for Dr. " + getFirstName());
        }
    }

    public void updateAvailability(List<String> newSlots) {
        setAvailableSlots(newSlots);
        System.out.println("Updated available slots for Dr. " + getFirstName());

    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        if (HelperUtils.isValidString(doctorId, 5, 20)) {
            this.doctorId = doctorId;
        } else {
            System.err.println("Validation Error: Invalid Doctor ID.");
        }
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if (HelperUtils.isValidString(specialization, 3, 50))
            this.specialization = specialization.trim();
        else
            System.out.println("Invalid specialization.");
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        if (HelperUtils.isValidString(qualification, 2))
            this.qualification = qualification.trim();
        else
            System.out.println("Invalid qualification.");
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        if (HelperUtils.isValidNumber(experienceYears, 0, 60))
            this.experienceYears = experienceYears;
        else
            System.out.println("Invalid years of experience.");
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        if (HelperUtils.isValidString(departmentId, 5, 20)) {
            this.departmentId = departmentId.trim();
        } else {
            System.err.println("Validation Error: Invalid department ID.");
        }
    }

    public Double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Double consultationFee) {
        if (consultationFee != null && HelperUtils.isPositive(consultationFee)) {
            this.consultationFee = consultationFee;
        } else {
            System.err.println("Validation Error: Consultation fee must be a positive amount.");
        }
    }

    public List<String> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<String> availableSlots) {
        this.availableSlots = (availableSlots != null) ? availableSlots : new ArrayList<>();
    }

    public List<Patient> getAssignedPatients() {
        return assignedPatients;
    }

    public void setAssignedPatients(List<Patient> assignedPatients) {
        this.assignedPatients = (assignedPatients != null) ? assignedPatients : new ArrayList<>();
    }

    public void updateFee(double fee) {
        setConsultationFee(fee);
        System.out.println("Updated Consultation Fee for doctor " + getFirstName() + " to: " + fee);
    }

    public void updateFee(double fee, String reason) {
        setConsultationFee(fee);
        System.out.println("Updated Consultation Fee for doctor " + getFirstName() + " to: " + fee
                + "reason" + reason);
    }

    public void addAvailability(String slot) {
        if (HelperUtils.isValidString(slot) && !availableSlots.contains(slot)) {
            availableSlots.add(slot);
            System.out.println("Added availability slot '" + slot);
        } else {
            System.out.println("cannot add slot: " + slot);
        }
    }

    public void addAvailability(List<String> slots) {
        if (HelperUtils.isNotNull(slots)) {
            for (String slot : slots) {
                if (HelperUtils.isValidString(slot) && !availableSlots.contains(slot)) {
                    availableSlots.add(slot);
                }
            }
            System.out.println("Added availability slots");
        }
    }


}
