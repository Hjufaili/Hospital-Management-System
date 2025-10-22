package Service;

import Entity.Appointment;
import Entity.MedicalRecord;
import Entity.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    private static List<Appointment> appointments = new ArrayList<>();


    public static void addAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.add(appointment);
            System.out.println("appointment is added: " + appointment);
        }
        System.out.println("Cannot add null appointment ");
    }

    public static void editAppointment(String appointmentId, Appointment updatedAppointments) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId().equals(appointmentId)) {
                appointments.set(i, updatedAppointments);
                System.out.println("Appointment is updated: " + appointmentId);
                return;
            }
        }
        System.out.println("appointmentId not found: " + appointmentId);
    }

    public static void removeAppointment(String appointmentsId) {
        boolean removed = appointments.removeIf(m -> m.getAppointmentId().equals(appointmentsId));

        if (removed) {
            System.out.println("Appointment is removed: " + appointmentsId);
        } else {
            System.out.println("Appointment not found: " + appointmentsId);
        }
    }

    public static Appointment getAppointmentById(String appointmentsId) {
        for (Appointment m : appointments) {
            if (m.getAppointmentId() != null && m.getAppointmentId().equals(appointmentsId)) {
                return m;
            }
        }
        System.out.println("Appointment not found: " + appointmentsId);
        return null;
    }

    public static void displayAllAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
            return;
        }
        System.out.println("All appointments ");
        for (Appointment a : appointments) {
            System.out.println(a);
        }
        System.out.println("------------------");
    }

    public static List<Appointment> getAppointmentsByPatient(String patientId) {
        List<Appointment> results = new ArrayList<>();

        for (Appointment m : appointments) {
            if (m.getPatientId().equals(patientId)) {
                results.add(m);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No Appointments found for patient:" + patientId);
        } else {
            System.out.println("Appointments found");
        }
        return results;
    }

    public static List<Appointment> getAppointmentsByDoctor(String doctorId) {
        List<Appointment> results = new ArrayList<>();

        for (Appointment m : appointments) {
            if (m.getDoctorId().equals(doctorId)) {
                results.add(m);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No appointments found for doctor:" + doctorId);
        } else {
            System.out.println("Appointments found");
        }
        return results;
    }

    public static List<Appointment> getAppointmentsByDate(LocalDate date) {
        List<Appointment> results = new ArrayList<>();

        for (Appointment m : appointments) {
            if (m.getAppointmentDate().equals(date)) {
                results.add(m);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No appointments found for Date:" + date);
        } else {
            System.out.println("Appointments found");
        }
        return results;
    }

    public static void rescheduleAppointment(String appointmentId, LocalDate newDate, String newTime) {
        Appointment a = getAppointmentById(appointmentId);
        if (a != null) {
            a.setAppointmentDate(newDate);
            a.setAppointmentTime(newTime);
            a.setStatus("Rescheduled");
            System.out.println("Appointment " + appointmentId + " rescheduled to " + newDate + " at " + newTime);
        }
    }

    public void cancelAppointment(String appointmentId) {
        Appointment a = getAppointmentById(appointmentId);
        if (a != null) {
            a.setStatus("Cancelled");
            System.out.println("Appointment cancelled: " + appointmentId);
        }
    }

    public void completeAppointment(String appointmentId) {
        Appointment a = getAppointmentById(appointmentId);
        if (a != null) {
            a.setStatus("Completed");
            System.out.println("Appointment completed" + appointmentId);
        }
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    public static Appointment createAppointment(String patientId, String doctorId, LocalDate date) {
        String id = "APT" + (appointments.size() + 1);
        Appointment a = new Appointment(id, patientId, doctorId, date, "Not Set", "Scheduled", "", "");
        addAppointment(a);
        return a;
    }

    public static Appointment createAppointment(String patientId, String doctorId, LocalDate date, String time) {
        String id = "APT" + (appointments.size() + 1);
        Appointment a = new Appointment(id, patientId, doctorId, date, time, "Scheduled", "", "");
        addAppointment(a);
        return a;
    }
}
