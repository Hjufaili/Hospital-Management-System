package Service;


import Entity.Nurse;

import java.util.ArrayList;
import java.util.List;

public class NurseService {
    private static List<Nurse> nurses = new ArrayList<>();


    public static void addNurse(Nurse nurse) {
        if (nurse != null) {
            nurses.add(nurse);
            System.out.println("Nurse added");
        } else {
            System.out.println("Cannot add null nurse");
        }
    }

    public static void editNurse(String nurseId, Nurse updatednurse) {
        if (!nurses.isEmpty()) {
            for (int i = 0; i < nurses.size(); i++) {
                if (nurses.get(i).getId().equals(nurseId)) {
                    nurses.set(i, updatednurse);
                }
            }
            System.out.println("No nurse found: " + nurseId);
        }
        System.out.println("No nurse");
    }


    public static void removeNurse(String nurseId) {
        boolean removed = nurses.removeIf(n -> n.getNurseId().equals(nurseId));

        if (removed) {
            System.out.println("Nurse removed: " + nurseId);
        } else {
            System.out.println("nurse not found: " + nurseId);
        }
    }

    public static Nurse getNurseById(String nurseId) {
        for (Nurse n : nurses) {
            if (n.getNurseId().equals(nurseId)) {
                return n;
            }
        }
        System.out.println("Nurse not found: " + nurseId);
        return null;
    }

    public static void displayAllNurses() {
        if (nurses.isEmpty()) {
            System.out.println("No nurses available");
            return;
        }
        for (Nurse n : nurses) {
            System.out.println(n);
        }
        System.out.println("---------------");
    }

    public static List<Nurse> getNursesByDepartment(String departmentId) {
        List<Nurse> results = new ArrayList<>();

        for (Nurse n : nurses) {
            if (n.getDepartmentId().equals(departmentId)) {
                results.add(n);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No nurses in department: "+ departmentId);
        }else {
            System.out.println("Nurses in department: "+ departmentId);
        }
        return results;
    }


}
