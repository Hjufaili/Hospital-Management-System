package Service;

import Entity.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    private static List<Doctor> doctors = new ArrayList<>();


    public static void addDoctor(Doctor doctor) {
        if (doctor != null) {
            doctors.add(doctor);
            System.out.println("Doctor is added");
        } else {
            System.out.println("Cannot null doctor");
        }
    }

    public static void editDoctor(String doctorId, Doctor updatedDoctor) {

        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getDoctorId().equals(doctorId)) {
                doctors.add(i, updatedDoctor);
                System.out.println("updated successfully!");
                return;
            }
        }
        System.out.println("Not found doctor:");
    }

    public static void removeDoctor(String doctorId) {
        boolean removed = doctors.removeIf(d -> d.getDoctorId().equals(doctorId));

        if (removed) {
            System.out.println("Removed doctor:  " + doctorId);
        } else {
            System.out.println("Doctor not found");
        }
    }

    public static Doctor getDoctorById(String doctorId) {
        for (Doctor d : doctors) {
            if (d.getDoctorId().equals(doctorId)) {
                return d;
            }
        }
        System.out.println("Doctor not found:" +doctorId);
        return null;
    }

    public static void displayAllDoctors() {
        if (doctors.isEmpty()){
            System.out.println("No doctor");
            return;
        }
        System.out.println("All doctors");
        for (Doctor d:doctors){
            System.out.println(d);
        }

    }

}
