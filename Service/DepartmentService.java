package Service;

import Entity.Department;
import Entity.Doctor;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DepartmentService implements Manageable, Searchable {
    private final List<Department> departments;

    private final DoctorService doctorService;

    public DepartmentService(DoctorService doctorService) {
        this.departments = new ArrayList<>();
        this.doctorService = doctorService;
    }


    public void addDepartment(Department department) {
        if (HelperUtils.isNull(department) || !HelperUtils.isValidString(department.getDepartmentId())) {
            System.err.println("Cannot add null department or department with invalid ID.");
            return;
        }

        for (Department existingDept : departments) {
            if (existingDept.getDepartmentId().equals(department.getDepartmentId())) {
                System.err.println("Cannot add department. ID already exists: " + department.getDepartmentId());
                return;
            }
        }

        departments.add(department);
        System.out.println("Department added: " + department.getDepartmentName());
    }

    public Department createDepartment(String departmentName, String headDoctorId, Integer bedCapacity) {
        if (!HelperUtils.isValidString(departmentName) || !HelperUtils.isValidString(headDoctorId)) {
            System.err.println("Failed to create department: Invalid name or head doctor ID.");
            return null;
        }

        // Use Lombok Builder pattern to create a new instance.
        // ID, collections, and bed defaults are set in the Entity.
        Department newDept = Department.builder()
                .departmentName(departmentName)
                .headDoctorId(headDoctorId)
                .bedCapacity(bedCapacity)
                .availableBeds(bedCapacity) // Initialize available beds to capacity
                .build();

        addDepartment(newDept);
        return newDept;
    }


    public void editDepartment(String departmentId, Department updatedDepartment) {
        if (!HelperUtils.isValidString(departmentId) || HelperUtils.isNull(updatedDepartment)) {
            System.err.println("Invalid ID or update object provided for editing.");
            return;
        }

        for (int i = 0; i < departments.size(); i++) {
            if (Objects.equals(departments.get(i).getDepartmentId(), departmentId)) {
                updatedDepartment.setDepartmentId(departmentId);
                departments.set(i, updatedDepartment);
                System.out.println("Department is updated: " + departmentId);
                return;
            }
        }
        System.out.println("departmentId not found: " + departmentId);
    }

    public void removeDepartment(String departmentId) {
        if (!HelperUtils.isValidString(departmentId)) {
            System.err.println("Invalid Department ID provided for removal.");
            return;
        }

        boolean removed = departments.removeIf(m -> m.getDepartmentId().equals(departmentId));

        if (removed) {
            System.out.println("Department is removed: " + departmentId);
        } else {
            System.out.println("Department not found: " + departmentId);
        }
    }

    public Department getDepartmentById(String departmentId) {
        for (Department m : departments) {
            if (m.getDepartmentId() != null && m.getDepartmentId().equals(departmentId)) {
                return m;
            }
        }
        System.out.println("Department not found: " + departmentId);
        return null;
    }

    public void displayAllDepartments() {
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

    public void assignDoctorToDepartment(String doctorId, String departmentId) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
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
        department.assignDoctor(doctor);
        System.out.println("Assigned Dr. " + doctor.getFirstName() +
                " to Department " + department.getDepartmentName());
    }

    public List<Department> getAllDepartments() {
        return departments;
    }

    @Override
    public void add(Object entity) {
        if (entity instanceof Department) {
            addDepartment((Department) entity);
        } else {
            System.err.println("Invalid entity type. Must be Department.");
        }
    }

    @Override
    public void remove(String id) {
        removeDepartment(id);
    }

    @Override
    public void getAll() {
        displayAllDepartments();
    }

    @Override
    public void search(String keyword) {
        if (!HelperUtils.isValidString(keyword)) {
            System.out.println("Invalid search keyword.");
            return;
        }

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
