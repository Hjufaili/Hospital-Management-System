package Interface;

import Entity.Appointment;

import java.time.LocalDate;

public interface Appointable {

    void scheduleAppointment(Appointment appointment);
    void cancelAppointment(String appointmentId);
    void rescheduleAppointment(String appointmentId, LocalDate newDate);
}
