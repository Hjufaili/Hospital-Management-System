package Service;


import Entity.Nurse;
import Interface.Manageable;
import Interface.Searchable;

import java.util.ArrayList;
import java.util.List;

public class NurseService implements Manageable, Searchable {
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
                    return;
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
        System.out.println("----All Nurses-----");
        for (Nurse n : nurses) {
            System.out.println(n);
        }
        System.out.println("---------------");
    }

    public static List<Nurse> getNursesByDepartment(String departmentId) {
        List<Nurse> results = new ArrayList<>();

        for (Nurse n : nurses) {
            if (n.getDepartmentId() != null && n.getDepartmentId().equalsIgnoreCase(departmentId)) {
                results.add(n);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No nurses in department: " + departmentId);
        } else {
            System.out.println("Nurses in department: " + departmentId);
        }
        return results;
    }


    public static List<Nurse> getNursesByShift(String shift) {
        List<Nurse> results = new ArrayList<>();

        for (Nurse n : nurses) {
            if (n.getShift() != null && n.getShift().equals(shift)) {
                results.add(n);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No nurses in shift: " + shift);
        } else {
            System.out.println("Nurses in shift: " + shift);
        }
        return results;
    }

    public List<Nurse> getAllNurses() {
        return nurses;
    }


    @Override
    public void add(Object entity) {
        if (entity instanceof Nurse) {
            Nurse nurse = (Nurse) entity;
            nurses.add(nurse);
            System.out.println("Nurse added: " + nurse.getFirstName() + " " + nurse.getLastName());
        } else {
            System.out.println("Invalid entity type. Must be Nurse.");
        }
    }

    @Override
    public void remove(String id) {
        boolean removed = nurses.removeIf(n -> n.getNurseId().equals(id));
        System.out.println(removed
                ? "Nurse removed successfully (ID: " + id + ")"
                : "Nurse not found with ID: " + id);
    }

    @Override
    public void getAll() {
        if (nurses.isEmpty()) {
            System.out.println("No nurses available.");
            return;
        }
        System.out.println("All Nurses:");
        for (Nurse n : nurses) {
            n.displaySummary();
        }
        System.out.println("--------------------------------");
    }

    @Override
    public void search(String keyword) {
        System.out.println("Searching for nurses containing: " + keyword);
        boolean found = false;
        for (Nurse n : nurses) {
            if ((n.getFirstName() != null && n.getFirstName().toLowerCase().contains(keyword.toLowerCase())) ||
                    (n.getLastName() != null && n.getLastName().toLowerCase().contains(keyword.toLowerCase())) ||
                    (n.getDepartmentId() != null && n.getDepartmentId().equalsIgnoreCase(keyword))) {
                n.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No nurses matched the keyword: " + keyword);

    }

    @Override
    public void searchById(String id) {
        Nurse nurse = getNurseById(id);
        if (nurse != null) nurse.displayInfo();
        else System.out.println("Nurse not found with ID: " + id);
    }


}
