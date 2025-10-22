package Entity;

import Interface.Displayable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment implements Displayable {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private String appointmentTime;
    private String status;
    private String reason;
    private String notes;

    public Appointment(String appointmentId, String patientId, String doctorId,
                       LocalDate appointmentDate, String appointmentTime,
                       String status, String reason, String notes) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.reason = reason;
        this.notes = notes;
    }

    public Appointment() {
    }


    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void displayInfo() {
        System.out.println("appointmentId:     " + appointmentId);
        System.out.println("patientId:     " + patientId);
        System.out.println("doctorId:     " + doctorId);
        System.out.println("appointmentDate:     " + appointmentDate);
        System.out.println("appointmentTime:     " + appointmentTime);
        System.out.println("status:     " + status);
        System.out.println("reason:     " + reason);
        System.out.println("notes:     " + notes);
    }

    @Override
    public void displaySummary() {
        System.out.println("Appointment " + appointmentId + ": " + appointmentDate + " at " +
                appointmentTime + " (" + status + ")");
    }


    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", appointmentTime='" + appointmentTime + '\'' +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    public void reschedule(LocalDate newDate, String newTime) {
        this.appointmentDate = newDate;
        this.appointmentTime = newTime;
        this.status = "Rescheduled";
        System.out.println("Appointment " + appointmentId + " rescheduled to " + newDate + " at " + newTime);
    }

    public void cancel() {
        this.status = "Cancelled";
        System.out.println("Appointment " + appointmentId + " has been cancelled.");
    }

    public void complete() {
        this.status = "Completed";
        System.out.println("Appointment " + appointmentId + " marked as completed.");

    }

    public void addNotes(String notes) {
        if (notes != null && !notes.isEmpty()) {
            this.notes = notes;
            System.out.println("Notes added: " + notes);
        }
    }

    public void addNotes(String notes, String addedBy) {
        if (notes != null && !notes.isEmpty()) {
            this.notes = notes + " (added by: " + addedBy + ")";
            System.out.println("Notes added by " + addedBy + ": " + notes);
        }
    }

    public void addNotes(String notes, String addedBy, LocalDateTime timestamp) {
        if (notes != null && !notes.isEmpty()) {
            this.notes = notes + " (added by: " + addedBy + " at " + timestamp + ")";
            System.out.println("Notes added by " + addedBy + " at " + timestamp + ": " + notes);
        }
    }

}