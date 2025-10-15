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
            if (doctors.get(i).getId().equals(doctorId)){
                doctors.add(i,updatedDoctor);
                System.out.println("updated successfully!");
                return;
            }
        }
        System.out.println("Not found doctor:");
    }

}
