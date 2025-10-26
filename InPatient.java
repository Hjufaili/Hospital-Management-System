package Entity;

import Interface.Billable;
import Interface.Displayable;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class InPatient extends Patient implements Displayable,Billable {

    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String roomNumber;
    private String bedNumber;
    private String admittingDoctorId;
    private Double dailyCharges;

    public InPatient(LocalDate admissionDate, LocalDate dischargeDate, String roomNumber, String bedNumber,
                     String admittingDoctorId, Double dailyCharges) {
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
        this.admittingDoctorId = admittingDoctorId;
        this.dailyCharges = dailyCharges;
    }

    public InPatient(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender,
                     String phoneNumber, String address, String email, String patientId,
                     String bloodGroup, List<String> allergies, String emergencyContact,
                     LocalDate registrationDate, String insuranceId, List<MedicalRecord> medicalRecords,
                     List<Appointment> appointments, LocalDate admissionDate, LocalDate dischargeDate,
                     String roomNumber, String bedNumber, String admittingDoctorId, Double dailyCharges) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email,
                patientId, bloodGroup, allergies, emergencyContact, registrationDate, insuranceId,
                medicalRecords, appointments);
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
        this.admittingDoctorId = admittingDoctorId;
        this.dailyCharges = dailyCharges;
    }

    public InPatient(String patientId, String bloodGroup, List<String> allergies,
                     String emergencyContact, LocalDate registrationDate, String insuranceId,
                     List<MedicalRecord> medicalRecords, List<Appointment> appointments,
                     LocalDate admissionDate, LocalDate dischargeDate, String roomNumber,
                     String bedNumber, String admittingDoctorId, Double dailyCharges) {
        super(patientId, bloodGroup, allergies, emergencyContact, registrationDate,
                insuranceId, medicalRecords, appointments);
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
        this.admittingDoctorId = admittingDoctorId;
        this.dailyCharges = dailyCharges;
    }

    public Double getDailyCharges() {
        return dailyCharges;
    }

    public void setDailyCharges(Double dailyCharges) {
        this.dailyCharges = dailyCharges;
    }

    public String getAdmittingDoctorId() {
        return admittingDoctorId;
    }

    public void setAdmittingDoctorId(String admittingDoctorId) {
        this.admittingDoctorId = admittingDoctorId;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public long calculateStayDuration() {
        if (dischargeDate == null) {
            System.out.println("Patient has not been discharged yet");
            return 0;
        }
        return ChronoUnit.DAYS.between(admissionDate, dischargeDate);
    }

    public double calculateTotalCharges() {
        long days = calculateStayDuration();
        double total = days * dailyCharges;
        System.out.println("Total Charges " + total);
        return total;
    }


    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Admission Date: " + admissionDate);
        System.out.println("Discharge Date: " + dischargeDate);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Bed Number: " + bedNumber);
        System.out.println("Admitting Doctor ID: " + admittingDoctorId);
        System.out.println("Daily Charges: " + dailyCharges);
        System.out.println("Stay Duration (days): " + calculateStayDuration());
        System.out.println("Total Charges: " + calculateTotalCharges());
    }

    @Override
    public void displaySummary() {
        System.out.println("InPatient " + getFirstName() + " " + getLastName() +
                " | Room: " + roomNumber + " | Stay: " + admissionDate + " → " + dischargeDate);
    }

    @Override
    public double calculateCharges() {
        long stayDays = ChronoUnit.DAYS.between(admissionDate, dischargeDate);
        return stayDays * dailyCharges;
    }

    @Override
    public void generateBill() {
        double total = calculateCharges();
        System.out.println("Bill Generated:");
        System.out.println("Patient: " + getFirstName() + " " + getLastName());
        System.out.println("Room: " + roomNumber + " | Bed: " + bedNumber);
        System.out.println("Stay Duration: " + calculateStayDuration() + " days");
        System.out.println("Daily Charge: " + dailyCharges);
        System.out.println("Total Amount: " + total);
    }

    @Override
    public void processPayment(double amount) {
        double total = calculateCharges();
        if (amount >= total) {
            System.out.println("Payment of " + amount + " processed successfully.");
        } else {
            System.out.println("Insufficient payment. Remaining balance: " + (total - amount));
        }
    }
}
