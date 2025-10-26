package Service;

import Entity.Department;
import Entity.Doctor;
import Interface.Manageable;
import Interface.Searchable;

import java.util.ArrayList;
import java.util.List;

public class DepartmentService implements Manageable, Searchable {
    private static List<Department> departments = new ArrayList<>();


    public static void addDepartment(Department department) {
        if (department != null) {
            departments.add(department);
            System.out.println("department is added: " + department);
        }
        System.out.println("Cannot add null department ");
    }

    public static void editDepartment(String departmentId, Department updateddepartment) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getDepartmentId().equals(departmentId)) {
                departments.set(i, updateddepartment);
                System.out.println("Department is updated: " + departmentId);
                return;
            }
        }
        System.out.println("departmentId not found: " + departmentId);
    }

    public static void removeDepartment(String departmentId) {
        boolean removed = departments.removeIf(m -> m.getDepartmentId().equals(departmentId));

        if (removed) {
            System.out.println("Department is removed: " + departmentId);
        } else {
            System.out.println("Department not found: " + departmentId);
        }
    }

    public static Department getDepartmentById(String departmentId) {
        for (Department m : departments) {
            if (m.getDepartmentId() != null && m.getDepartmentId().equals(departmentId)) {
                return m;
            }
        }
        System.out.println("Department not found: " + departmentId);
        return null;
    }

    public static void displayAllDepartments() {
        if (departments.isEmpty()) {
            System.out.println("No departments available.");
            return;
        }
        System.out.println("All departments ");
        for (Department a : departments) {
            System.out.println(a);
        }
        System.out.println("------------------");
    }

    public static void assignDoctorToDepartment(String doctorId, String departmentId) {
        Doctor doctor = DoctorService.getDoctorById(doctorId);
        Department department = getDepartmentById(departmentId);

        if (doctor == null) {
            System.out.println("Doctor not found: " + doctorId);
            return;
        }
        if (department == null) {
            System.out.println("Department not found: " + departmentId);
            return;
        }

        doctor.setDepartmentId(departmentId);
        System.out.println("Assigned Dr. " + doctor.getFirstName() +
                " to Department " + department.getDepartmentName());
    }

    public List<Department> getAllDepartments() {
        return departments;
    }

    @Override
    public void add(Object entity) {
        if (entity instanceof Department) {
            Department dept = (Department) entity;
            departments.add(dept);
            System.out.println("Department added: " + dept.getDepartmentName());
        } else {
            System.out.println("Invalid entity type. Must be Department.");
        }
    }

    @Override
    public void remove(String id) {
        boolean removed = departments.removeIf(d -> d.getDepartmentId().equals(id));
        System.out.println(removed
                ? "Department removed successfully (ID: " + id + ")"
                : "Department not found with ID: " + id);
    }

    @Override
    public void getAll() {
        if (departments.isEmpty()) {
            System.out.println("No departments available.");
            return;
        }
        System.out.println("All Departments:");
        for (Department d : departments) {
            d.displaySummary();
        }
        System.out.println("--------------------------------");
    }

    @Override
    public void search(String keyword) {
        System.out.println("Searching departments with keyword: " + keyword);
        boolean found = false;
        for (Department d : departments) {
            if (d.getDepartmentName() != null && d.getDepartmentName().toLowerCase().contains(keyword.toLowerCase())) {
                d.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No departments found for: " + keyword);
    }

    @Override
    public void searchById(String id) {
        Department dept = getDepartmentById(id);
        if (dept != null) dept.displayInfo();
        else System.out.println("Department not found with ID: " + id);
    }
}
