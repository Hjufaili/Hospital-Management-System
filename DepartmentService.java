package Service;

import Entity.Appointment;
import Entity.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
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
}
