package Service;


import Entity.Nurse;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NurseService implements Manageable, Searchable {
    private List<Nurse> nurses;

    public NurseService() {
        this.nurses = new ArrayList<>();
    }


    public void addNurse(Nurse nurse) {
        if (HelperUtils.isNull(nurse) || !HelperUtils.isValidString(nurse.getNurseId())) {
            System.err.println("Cannot add null nurse or nurse with invalid ID.");
            return;
        }

        for (Nurse existingNurse : nurses) {
            if (existingNurse.getNurseId().equals(nurse.getNurseId())) {
                System.err.println("Cannot add nurse. ID already exists: " + nurse.getNurseId());
                return;
            }
        }

        nurses.add(nurse);
        System.out.println("Nurse added successfully: " + nurse.getFirstName() + " " + nurse.getLastName());
    }

    public void editNurse(String nurseId, Nurse updatednurse) {
        if (!HelperUtils.isValidString(nurseId) || HelperUtils.isNull(updatednurse)) {
            System.err.println("Invalid ID or update object provided for editing.");
            return;
        }
        for (int i = 0; i < nurses.size(); i++) {
            if (nurses.get(i).getId().equals(nurseId)) {
                nurses.set(i, updatednurse);
                System.out.println(" updated successfully!");
                return;
            }
        }
        System.out.println("No nurse found: " + nurseId);
    }


    public void removeNurse(String nurseId) {

        if (!HelperUtils.isValidString(nurseId)) {
            System.err.println("Invalid nurse Id provided for removal.");
            return;
        }
        boolean removed = nurses.removeIf(n -> n.getNurseId().equals(nurseId));

        if (removed) {
            System.out.println("Nurse removed: " + nurseId);
        } else {
            System.out.println("nurse not found: " + nurseId);
        }
    }

    public Nurse getNurseById(String nurseId) {
        for (Nurse n : nurses) {
            if (n.getNurseId().equals(nurseId)) {
                return n;
            }
        }
        System.out.println("Nurse not found: " + nurseId);
        return null;
    }

    public void displayAllNurses() {
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

    public List<Nurse> getNursesByDepartment(String departmentId) {
        if (!HelperUtils.isValidString(departmentId)) return new ArrayList<>();
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


    public List<Nurse> getNursesByShift(String shift) {
        if (!HelperUtils.isValidString(shift)) return new ArrayList<>();
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

    public void addNurse(String firstName, String lastName, String departmentId) {
        if (HelperUtils.isValidString(firstName) && HelperUtils.isValidString(lastName) && HelperUtils.isValidString(departmentId)) {

            // Create a new Nurse object using a suitable constructor (or setters)
            Nurse newNurse = new Nurse(
                    HelperUtils.generateId("PER"), firstName, lastName, (LocalDate) null,
                    "N/A", "N/A", "N/A", "N/A", // Minimal Person fields

                    HelperUtils.generateId("NUR"), departmentId, "Day", "N/A", 0, 0 // Minimal Nurse fields
            );

            addNurse(newNurse); // Delegate to the safe core addNurse(Nurse) method
            System.out.println("Nurse added to department: " + departmentId);
        } else {
            System.err.println("Failed to add nurse: Invalid name or department ID.");
        }
    }


    @Override
    public void add(Object entity) {
        if (entity instanceof Nurse) {
            Nurse nurse = (Nurse) entity;
            nurses.add(nurse);
            System.out.println("Nurse added: " + nurse.getFirstName() + " " + nurse.getLastName());
        } else if (HelperUtils.isNull(entity)) {
            System.err.println("Entity is null.");
        } else {
            System.out.println("Invalid entity type. Must be Nurse.");
        }
    }

    @Override
    public void remove(String id) {
        removeNurse(id);
    }

    @Override
    public void getAll() {
        displayAllNurses();
    }

    @Override
    public void search(String keyword) {
        System.out.println("Searching for nurses containing: " + keyword);
        if (!HelperUtils.isValidString(keyword)) return;
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
        if (HelperUtils.isNotNull(nurse)) {
            System.out.println("Patient Found by ID: " + id);
            nurse.displayInfo();
        }
    }


}
