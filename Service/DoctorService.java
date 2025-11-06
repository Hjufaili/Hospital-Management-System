package Service;

import Entity.Doctor;
import Entity.Patient;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoctorService implements Manageable, Searchable {
    private  List<Doctor> doctors ;

    public DoctorService(){
        this.doctors=new ArrayList<>();
    }


    public  void addDoctor(Doctor doctor) {
        if (HelperUtils.isNull(doctor) || !HelperUtils.isValidString(doctor.getDoctorId())) {
            System.err.println("Cannot add null doctor or doctor with invalid ID.");
            return;
        }

        for (Doctor existingDoctor : doctors) {
            if (existingDoctor.getDoctorId().equals(doctor.getDoctorId())) {
                System.err.println("Cannot add doctor. ID already exists: " + doctor.getDoctorId());
                return;
            }
        }

        if (HelperUtils.isValidString(doctor.getSpecialization(), 3) && HelperUtils.isPositive(doctor.getExperienceYears())) {
            doctors.add(doctor);
            System.out.println("Doctor added successfully: " + doctor.getFirstName() + " (" + doctor.getSpecialization() + ")");
        } else {
            System.err.println("Invalid doctor details. Check specialization (min 3 chars) and experience (positive).");
        }
    }

    public  void editDoctor(String doctorId, Doctor updatedDoctor) {
        if (!HelperUtils.isValidString(doctorId) || HelperUtils.isNull(updatedDoctor)) {
            System.err.println("Invalid ID or update object provided for editing.");
            return;
        }
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getDoctorId().equals(doctorId)) {
                doctors.add(i, updatedDoctor);
                System.out.println("updated successfully!");
                return;
            }
        }
        System.out.println("Not found doctor:");
    }

    public void removeDoctor(String doctorId) {
        if (!HelperUtils.isValidString(doctorId)) {
            System.err.println("Invalid Doctor ID provided for removal.");
            return;
        }
        boolean removed = doctors.removeIf(d -> d.getDoctorId().equalsIgnoreCase(doctorId));
        System.out.println(removed ? "Doctor removed." : "Doctor not found.");
    }

    public  Doctor getDoctorById(String doctorId) {
        for (Doctor d : doctors) {
            if (d.getDoctorId().equals(doctorId)) {
                return d;
            }
        }
        System.out.println("Doctor not found:" + doctorId);
        return null;
    }

    public  void displayAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctor");
            return;
        }
        System.out.println("-----All doctors-----");
        for (Doctor d : doctors) {
            System.out.println(d);
        }
    }

    public  List<Doctor> getDoctorsBySpecialization(String specialization) {
        if (!HelperUtils.isValidString(specialization)) return new ArrayList<>();

        List<Doctor> results = new ArrayList<>();
        for (Doctor d : doctors) {
            if (d.getSpecialization().equalsIgnoreCase(specialization) && d.getSpecialization() != null) {
                results.add(d);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No doctor in specialization:  " + specialization);
        } else {
            System.out.println("All doctors in specialization" + specialization);
        }

        return results;
    }

    public  List<Doctor> getAvailableDoctors() {
        List<Doctor> available = new ArrayList<>();
        for (Doctor d : doctors) {
            if (d.getAvailableSlots() != null && !d.getAvailableSlots().isEmpty()) {
                available.add(d);
            }
        }

        if (available.isEmpty()) {
            System.out.println("No doctors currently available.");
        } else {
            System.out.println(" available doctors:" + available.size() + " doctors");
        }

        return available;
    }

    public  List<Doctor> getAllDoctor() {
        return doctors;
    }

    public  void addDoctor(String name, String specialization, String phone) {
        if (HelperUtils.isValidString(name) && HelperUtils.isValidString(specialization) && HelperUtils.isValidString(phone,8)) {
            Doctor newDoctor = new Doctor(
                    HelperUtils.generateId("PER"), name, "N/A", (LocalDate) null,
                    "N/A", phone, "N/A", "N/A",

                    HelperUtils.generateId("DOC"), specialization, "N/A", 0,"N/A", 0.0,
                    new ArrayList<>(), new ArrayList<>()
            );
            addDoctor(newDoctor);
        } else {
            System.err.println("Failed to add doctor: Invalid name, specialization, or phone.");
        }
    }



    public  void addDoctor(String name, String specialization, String phone, double consultationFee) {
        Doctor doctor = new Doctor();
        if (name != null && specialization != null && phone != null) {
            doctor.setFirstName(name);
            doctor.setSpecialization(specialization);
            doctor.setPhoneNumber(phone);
            doctor.setConsultationFee(consultationFee);
            doctors.add(doctor);
            System.out.println("Doctor added: " + name);
        }
    }

    public void assignPatient(String doctorId, String patientId) {
        Doctor doctor = getDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found: " + doctorId);
            return;
        }
        List<Patient> assignedPatients = doctor.getAssignedPatients();
        if (assignedPatients == null) {
            assignedPatients = new ArrayList<>();
            doctor.setAssignedPatients(assignedPatients);
        }

        Patient patient = new Patient();
        patient.setPatientId(patientId);
        assignedPatients.add(patient);
        System.out.println("Assigned patient " + patientId + " to doctor " + doctorId);
    }

    public  void assignPatient(Doctor doctor, Patient patient) {
        if (doctor == null || patient == null) {
            System.out.println("Doctor or Patient is null.");
            return;
        }
        List<Patient> assignedPatients = doctor.getAssignedPatients();
        if (assignedPatients == null) {
            assignedPatients = new ArrayList<>();
            doctor.setAssignedPatients(assignedPatients);
        }
        assignedPatients.add(patient);
        System.out.println("Assigned " + patient.getPatientId() + " to Dr. " + doctor.getFirstName());
    }

    public void assignPatient(String doctorId, List<String> patientIds) {
        Doctor doctor = getDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found: " + doctorId);
            return;
        }
        if (patientIds == null || patientIds.isEmpty()) {
            System.out.println("No patient IDs provided for assignment.");
            return;
        }

        List<Patient> assignedPatients = doctor.getAssignedPatients();
        if (assignedPatients == null) {
            assignedPatients = new ArrayList<>();
            doctor.setAssignedPatients(assignedPatients);
        }

        for (String p : patientIds) {
            Patient patient = new Patient();
            patient.setPatientId(p);
            assignedPatients.add(patient);
        }

        System.out.println("Bulk assigned " + patientIds.size() + " patients to Dr. " + doctorId);
    }

    public void displayDoctors(String specialization) {
        boolean found = false;
        System.out.println("----- Doctors in Specialization: " + specialization + " -----");
        for (Doctor d : doctors) {
            if (d.getSpecialization() != null && d.getSpecialization().equalsIgnoreCase(specialization)) {
                d.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No doctors found in specialization: " + specialization);
    }

    public void displayDoctors(String departmentId, boolean showAvailableOnly) {
        boolean found = false;
        System.out.println("----- Doctors in department: " + departmentId + " -----");
        for (Doctor d : doctors) {
            if (d.getDepartmentId() != null && d.getDepartmentId().equalsIgnoreCase(departmentId)
                    && (!showAvailableOnly || (d.getAvailableSlots() != null &&
                    !d.getAvailableSlots().isEmpty()))) {
                d.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No doctors match.");
        }
    }

    @Override
    public void add(Object entity) {
        if (entity instanceof Doctor) {
            addDoctor((Doctor) entity);
        } else {
            System.err.println("Invalid entity type. Must be Doctor.");
        }
    }

    @Override
    public void remove(String id) {
        removeDoctor(id);
    }

    @Override
    public void getAll() {
        displayAllDoctors();
    }

    @Override
    public void search(String keyword) {
        if (!HelperUtils.isValidString(keyword)) {
            System.out.println("Invalid search keyword.");
            return;
        }
        boolean found = false;
        for (Doctor d : doctors) {
            if (d.getSpecialization().equalsIgnoreCase(keyword)) {
                System.out.println("Found: " + d.getFirstName() + " - " + d.getSpecialization());
                found = true;
            }
        }
        if (!found) System.out.println("No matching doctors.");

    }

    @Override
    public void searchById(String id) {
        Doctor doctor = getDoctorById(id);
        if (doctor != null) doctor.displayInfo();
        else System.out.println("Doctor not found with ID: " + id);
    }
}


