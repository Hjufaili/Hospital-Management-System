package Entity;

import Interface.Displayable;
import Utils.HelperUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@SuperBuilder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Person implements Displayable {

    @EqualsAndHashCode.Include
    private String id = HelperUtils.generateId("PER");

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;

    public Person(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String address, String email) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
        setGender(gender);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setAddress(address);
    }

    public Person(String first, String last, String gender, String phone, String email) {
        setFirstName(first);
        setLastName(last);
        setGender(gender);
        setPhoneNumber(phone);
        setEmail(email);
    }

    public void setId(String id) {
        if (HelperUtils.isValidString(id, 3, 50)) {
            this.id = id;
        } else {
            System.err.println("Validation Error: Invalid ID format or length provided.");
            this.id = HelperUtils.generateId("PER");
        }
    }

    public void setAddress(String address) {
        if (HelperUtils.isNotNull(address)) {
            this.address = address;
        } else {
            System.err.println("Validation Error: Address cannot be null or empty.");
        }
    }

    public void setEmail(String email) {
        if (HelperUtils.isNotNull(email)) {
            this.email = email;
        } else {
            System.err.println("Validation Error: Email cannot be null or empty.");
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (HelperUtils.isValidString(phoneNumber, 8, 8)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.err.println("Validation Error: Invalid phone number.");
        }
    }

    public void setGender(String gender) {
        if (HelperUtils.isValidString(gender) && (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("other"))) {
            this.gender = gender;
        } else {
            System.err.println("Validation Error: Invalid gender.");
        }
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (HelperUtils.isValidAge(dateOfBirth)) {
            this.dateOfBirth = dateOfBirth;
        } else {
            System.err.println("Validation Error: Invalid or future date of birth provided.");
        }
    }

    public void setLastName(String lastName) {
        if (HelperUtils.isValidString(lastName, 2, 50)) {
            this.lastName = lastName;
        } else {
            System.err.println("Validation Error: Invalid last name.");
        }
    }

    public void setFirstName(String firstName) {
        if (HelperUtils.isValidString(firstName, 2, 50)) {
            this.firstName = firstName;
        } else {
            System.err.println("Validation Error: Invalid first name.");
        }
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


}