package Entity;

import Interface.Displayable;
import Utils.HelperUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class Appointment implements Displayable {
    private String appointmentId = Utils.HelperUtils.generateId("APP") ;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status = "SCHEDULED";
    private String reason;
    private String notes = "";
    private static final List<String> VALID_STATUSES = Arrays.asList("SCHEDULED", "RESCHEDULED", "CANCELLED", "COMPLETED", "PENDING");


//    public Appointment(String patientId, String doctorId, LocalDate appointmentDate,
//                       LocalTime appointmentTime, String reason) { // Simplified parameters
//
//        this.appointmentId = Utils.HelperUtils.generateId("APP");
//
//        setPatientId(patientId);
//        setDoctorId(doctorId);
//        setAppointmentDate(appointmentDate);
//        setAppointmentTime(appointmentTime);
//        setReason(reason);
//
//        this.status = "Scheduled";
//        this.notes = "";
//    }
//
//
//    public Appointment(String appointmentId, String patientId, String doctorId,
//                       LocalDate appointmentDate, LocalTime appointmentTime,
//                       String status, String reason, String notes) {
//        setAppointmentId(appointmentId);
//        setPatientId(patientId);
//        setDoctorId(doctorId);
//        setAppointmentDate(appointmentDate);
//        setAppointmentTime(appointmentTime);
//        setStatus(status);
//        setReason(reason);
//        setNotes(notes);
//    }
//
//    public Appointment() {
//    }


    public void setAppointmentId(String appointmentId) {
        if (HelperUtils.isValidString(appointmentId, 5, 20)) {
            this.appointmentId = appointmentId;
        } else {
            System.err.println("Validation Error: Invalid Appointment ID.");
        }
    }

    public void setPatientId(String patientId) {
        if (HelperUtils.isValidString(patientId, 5, 20)) {
            this.patientId = patientId;
        } else {
            System.err.println("Validation Error: Invalid Patient ID for appointment.");
        }
    }

    public void setDoctorId(String doctorId) {
        if (HelperUtils.isValidString(doctorId, 5, 20)) {
            this.doctorId = doctorId;
        } else {
            System.err.println("Validation Error: Invalid Doctor ID for appointment.");
        }
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        if (HelperUtils.isNotNull(appointmentDate) && !appointmentDate.isBefore(LocalDate.now().minusDays(365))) {
            this.appointmentDate = appointmentDate;
        } else {
            System.err.println("Validation Error: Appointment date cannot be null or a very old date.");
        }
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        if (HelperUtils.isNotNull(appointmentTime)) {
            this.appointmentTime = appointmentTime;
        } else {
            System.err.println("Validation Error: Appointment time cannot be null.");
        }
    }

    public void setStatus(String status) {
        if (HelperUtils.isValidString(status) && VALID_STATUSES.contains(status.toUpperCase())) {
            this.status = status;
        } else {
            System.err.println("Invalid status provided.");
        }
    }

    public void setReason(String reason) {
        this.reason = reason;
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


//    @Override
//    public String toString() {
//        return "Appointment{" +
//                "appointmentId='" + appointmentId + '\'' +
//                ", patientId='" + patientId + '\'' +
//                ", doctorId='" + doctorId + '\'' +
//                ", appointmentDate=" + appointmentDate +
//                ", appointmentTime='" + appointmentTime + '\'' +
//                ", status='" + status + '\'' +
//                ", reason='" + reason + '\'' +
//                ", notes='" + notes + '\'' +
//                '}';
//    }

    public void reschedule(LocalDate newDate, LocalTime newTime) {
        if (HelperUtils.isNotNull(newDate) && HelperUtils.isNotNull(newTime) && newDate.isAfter(LocalDate.now().minusDays(1))) {
            setAppointmentDate(newDate);
            setAppointmentTime(newTime);
            setStatus("RESCHEDULED");
            System.out.println("Appointment " + appointmentId + " rescheduled to " + newDate + " at " + newTime);
        } else {
            System.err.println("Reschedule failed. New date/time is invalid or in the past.");
        }
    }

    public void cancel() {
        setStatus("CANCELLED");
        System.out.println("Appointment " + appointmentId + " has been cancelled.");
    }

    public void complete() {
        setStatus("COMPLETED");
        System.out.println("Appointment " + appointmentId + " marked as completed.");

    }

    public void addNotes(String notes) {
        if (Utils.HelperUtils.isValidString(notes)) { // Use the HelperUtils method
            this.notes = notes;
            System.out.println("Notes added: " + notes);
        } else {
            System.err.println("Notes cannot be empty.");
        }
    }

    public void addNotes(String notes, String addedBy) {
        if (HelperUtils.isValidString(notes, 5)) {
            String author = HelperUtils.isValidString(addedBy) ? addedBy : "Unknown";
            this.notes = notes + " (Added by: " + author + ")";
            System.out.println("Notes added by " + author + ": " + notes);
        } else {
            System.err.println("Notes cannot be empty or too short.");
        }
    }

    public void addNotes(String notes, String addedBy, LocalDateTime timestamp) {
        if (HelperUtils.isValidString(notes, 5) && HelperUtils.isNotNull(timestamp)) {
            String author = HelperUtils.isValidString(addedBy) ? addedBy : "Unknown";
            this.notes = notes + " (Added by: " + author + " at " + timestamp.toLocalTime() +
                    " on " + timestamp.toLocalDate() + ")";
            System.out.println("Notes added by " + author + " with timestamp.");
        } else {
            System.err.println("Notes, author, or timestamp is invalid.");
        }
    }

}