package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Billing implements Displayable {

    private String billingId;
    private String patientId;
    private String doctorId;
    private String appointmentId;
    private LocalDate billingDate;
    private List<BillingItem> items;
    private Double totalAmount;
    private Double insuranceCoverage;
    private Double patientDue;
    private String status;

    private static final List<String> VALID_STATUSES = List.of("UNPAID", "PENDING_INSURANCE", "PAID", "OVERDUE", "SETTLED");

    public static class BillingItem {
        private String description;
        private double fee;
        private int quantity;

        public BillingItem(String description, double fee, int quantity) {
            this.description = description;
            this.fee = fee;
            this.quantity = quantity;
        }

        public double getSubtotal() {
            return fee * quantity;
        }

        @Override
        public String toString() {
            return String.format( description + " (" + quantity + "): "+ getSubtotal());
        }
    }



    public Billing() {
        this.billingId = HelperUtils.generateId("BIL");
        this.billingDate = LocalDate.now();
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
        this.insuranceCoverage = 0.0;
        this.patientDue = 0.0;
        this.status = "UNPAID";
    }

    public Billing(String billingId, String patientId, String doctorId, String appointmentId,
                   LocalDate billingDate, List<BillingItem> items, Double totalAmount,
                   Double insuranceCoverage, Double patientDue, String status) {

        setBillingId(billingId);
        setPatientId(patientId);
        setDoctorId(doctorId);
        setAppointmentId(appointmentId);
        setBillingDate(billingDate);
        setItems(items);
        setTotalAmount(totalAmount);
        setInsuranceCoverage(insuranceCoverage);
        setPatientDue(patientDue);
        setStatus(status);
    }

    public Billing(String patientId, String doctorId, String appointmentId) {
        this(); // Calls default constructor to initialize lists, ID, and date
        setPatientId(patientId);
        setDoctorId(doctorId);
        setAppointmentId(appointmentId);
    }


    public String getBillingId() {
        return billingId;
    }

    public void setBillingId(String billingId) {
        if (HelperUtils.isValidString(billingId, 5, 20)) {
            this.billingId = billingId;
        } else {
            System.err.println("Validation Error: Invalid Billing ID.");
        }
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        if (HelperUtils.isValidString(patientId, 5, 20)) {
            this.patientId = patientId;
        } else {
            System.err.println("Validation Error: Invalid Patient ID for billing.");
        }
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        if (HelperUtils.isValidString(doctorId, 5, 20)) {
            this.doctorId = doctorId;
        } else {
            this.doctorId = null; // Doctor ID can be optional
        }
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        if (HelperUtils.isValidString(appointmentId, 5, 20)) {
            this.appointmentId = appointmentId;
        } else {
            this.appointmentId = null; // Appointment ID can be optional
        }
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(LocalDate billingDate) {
        if (HelperUtils.isNotNull(billingDate)) {
            this.billingDate = billingDate;
        } else {
            System.err.println("Validation Error: Billing date cannot be null.");
        }
    }

    public List<BillingItem> getItems() {
        return items;
    }

    public void setItems(List<BillingItem> items) {
        this.items = (items != null) ? items : new ArrayList<>();
        calculateTotal();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        if (!HelperUtils.isNegative(totalAmount)) {
            this.totalAmount = totalAmount;
        } else {
            System.err.println("Validation Error: Total amount cannot be negative.");
        }
    }

    public Double getInsuranceCoverage() {
        return insuranceCoverage;
    }

    public void setInsuranceCoverage(Double insuranceCoverage) {
        if (!HelperUtils.isNegative(insuranceCoverage)) {
            this.insuranceCoverage = insuranceCoverage;
        } else {
            System.err.println("Validation Error: Insurance coverage cannot be negative.");
        }
    }

    public Double getPatientDue() {
        return patientDue;
    }

    public void setPatientDue(Double patientDue) {
        if (!HelperUtils.isNegative(patientDue)) {
            this.patientDue = patientDue;
        } else {
            System.err.println("Validation Error: Patient due amount cannot be negative.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (HelperUtils.isValidString(status) && VALID_STATUSES.contains(status.toUpperCase())) {
            this.status = status.toUpperCase();
        } else {
            System.err.println("Validation Error: Invalid status provided. Must be one of: " + String.join(", ", VALID_STATUSES));
        }
    }


    public void calculateTotal() {
        double total = items.stream().mapToDouble(BillingItem::getSubtotal).sum();
        setTotalAmount(total);
        updatePatientDue();
        System.out.println("Total bill calculated: $" + String.format("%.2f", totalAmount));
    }

    public void processInsurance(double coveragePercentage) {
        if (coveragePercentage >= 0 && coveragePercentage <= 100) {
            double coverageAmount = totalAmount * (coveragePercentage / 100.0);
            setInsuranceCoverage(coverageAmount);
            updatePatientDue();
            setStatus("PENDING_INSURANCE");
            System.out.println("Insurance coverage processed. Covered: $" + String.format("%.2f", insuranceCoverage));
        } else {
            System.err.println("Invalid coverage percentage. Must be between 0 and 100.");
        }
    }

    public void markAsPaid() {
        if (patientDue <= 0.0) {
            setStatus("PAID");
            System.out.println("Bill marked as PAID in full.");
        } else {
            System.err.println("Cannot mark as paid. Patient still owes $" + String.format("%.2f", patientDue));
        }
    }

    public void markAsPaid(double amountPaid) {
        if (HelperUtils.isPositive(amountPaid)) {
            double newDue = patientDue - amountPaid;
            setPatientDue(Math.max(0, newDue)); // Ensures due is not negative
            if (newDue <= 0) {
                setStatus("PAID");
                System.out.println("Payment of $" + String.format("%.2f", amountPaid) + " received. Bill is now PAID.");
            } else {
                System.out.println("Partial payment of $" + String.format("%.2f", amountPaid) + " received. Remaining due: $" + String.format("%.2f", patientDue));
            }
        } else {
            System.err.println("Invalid payment amount.");
        }
    }


    private void updatePatientDue() {
        double due = totalAmount - insuranceCoverage;
        setPatientDue(Math.max(0, due));
    }


    public void addItem(String description, double fee, int quantity) {
        if (HelperUtils.isValidString(description) && HelperUtils.isPositive(fee) && HelperUtils.isPositive(quantity)) {
            items.add(new BillingItem(description, fee, quantity));
            calculateTotal();
        } else {
            System.err.println("Failed to add item. Invalid description, fee, or quantity.");
        }
    }

    public void addItem(BillingItem item) {
        if (HelperUtils.isNotNull(item)) {
            addItem(item.description, item.fee, item.quantity);
        } else {
            System.err.println("Cannot add a null BillingItem.");
        }
    }


    @Override
    public void displayInfo() {
        System.out.println("=====================================");
        System.out.println("            BILLING STATEMENT        ");
        System.out.println("=====================================");
        System.out.printf("Billing ID: "+ billingId);
        System.out.printf("Patient ID: "+patientId);
        System.out.printf("Billing Date: "+ billingDate);
        System.out.printf("Status: "+ status);

        System.out.println("\n--- Line Items ---");
        if (items.isEmpty()) {
            System.out.println("  No items billed yet.");
        } else {
            items.forEach(item -> System.out.printf("  - %s: $%.2f (x%d) = $%.2f%n",
                    item.description, item.fee, item.quantity, item.getSubtotal()));
        }

        System.out.println("\n--- Summary ---");
        System.out.printf("Total Charges: "+ totalAmount);
        System.out.printf("Insurance Coverage: "+insuranceCoverage);
        System.out.printf("Patient Due: " +patientDue);
        System.out.println("-------------------------------------");
    }


    @Override
    public void displaySummary() {
        System.out.println(String.format("BILL: %-10s | Patient: %-10s | Date: %s | Total: $%.2f | Due: $%.2f | Status: %s",
                billingId, patientId, billingDate, totalAmount, patientDue, status));
    }

    // --- Standard Java Methods ---

    @Override
    public String toString() {
        return "Billing{" +
                "billingId='" + billingId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", totalAmount=" + totalAmount +
                ", patientDue=" + patientDue +
                ", status='" + status + '\'' +
                '}';
    }
}