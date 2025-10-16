package Service;

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
}
