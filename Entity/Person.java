package Entity;

import Interface.Displayable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Person implements Displayable {

    private String id;
    private static String firstName;
    private static String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private static String phoneNumber;
    private String email;
    private String address;

    public Person(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String address, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    public Person() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void displayInfo() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dob = (dateOfBirth != null) ? dateOfBirth.format(fmt) : "N/A";

        System.out.println("----- Person Info -----");
        System.out.println("ID:           " + id);
        System.out.println("Name:         " + firstName + " " + lastName);
        System.out.println("DOB:          " + dob);
        System.out.println("Gender:       " + gender);
        System.out.println("Phone:        " + phoneNumber);
        System.out.println("Email:        " + email);
        System.out.println("Address:      " + address);
        System.out.println("-----------------------");
    }

    @Override
    public void displaySummary() {
        System.out.println(" " + firstName + " " + lastName + " | " + gender + " | " + phoneNumber);
    }

    @Override
    public String toString() {
        return "Entity.Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dataOdBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
