package Service;

import Entity.Appointment;
import Entity.MedicalRecord;
import Entity.Patient;
import Interface.Appointable;
import Interface.Manageable;
import Interface.Searchable;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService implements Manageable, Searchable, Appointable {

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
        } else {
            System.out.println("Appointment not found: " + appointmentId);
        }
    }

    @Override
    public void scheduleAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.add(appointment);
            System.out.println("Appointment created: " + appointment.getAppointmentId());
        } else {
            System.out.println("Cannot create null appointment.");
        }
    }

    public void cancelAppointment(String appointmentId) {
        Appointment a = getAppointmentById(appointmentId);
        if (a != null) {
            a.setStatus("Cancelled");
            System.out.println("Appointment cancelled: " + appointmentId);
        } else {
            System.out.println("Appointment not found: " + appointmentId);
        }
    }

    public void completeAppointment(String appointmentId) {
        Appointment a = getAppointmentById(appointmentId);
        if (a != null) {
            a.setStatus("Completed");
            System.out.println("Appointment completed" + appointmentId);
        } else {
            System.out.println("Appointment not found: " + appointmentId);
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

    public static void createAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.add(appointment);
            System.out.println("Appointment created: " + appointment.getAppointmentId());
        } else {
            System.out.println("Cannot create null appointment.");
        }
    }

    public void rescheduleAppointment(String appointmentId, LocalDate newDate) {
        Appointment a = getAppointmentById(appointmentId);
        if (a != null) {
            a.setAppointmentDate(newDate);
            a.setStatus("Rescheduled");
            System.out.println("Appointment " + appointmentId + " rescheduled to " + newDate);
        } else {
            System.out.println("Appointment not found: " + appointmentId);
        }
    }

    public static void rescheduleAppointment(Appointment appointment, LocalDate newDate, String newTime, String reason) {
        if (appointment != null) {
            appointment.setAppointmentDate(newDate);
            appointment.setAppointmentTime(newTime);
            appointment.setStatus("Rescheduled");
            appointment.setReason(reason);
            System.out.println("Appointment " + appointment.getAppointmentId() +
                    " rescheduled to " + newDate + " at " + newTime +
                    ". Reason: " + reason);
        }
    }

    public static void displayAppointments(LocalDate date) {
        List<Appointment> list = getAppointmentsByDate(date);
        if (!list.isEmpty()) {
            System.out.println("Appointments on " + date + ":");
            for (Appointment a : list) {
                a.displayInfo();
                System.out.println("----------------");
            }
        } else {
            System.out.println("No Appointments ");
        }
    }

    public static void displayAppointments(String doctorId, LocalDate startDate, LocalDate endDate) {
        System.out.println("Appointments for Doctor " + doctorId + " between " + startDate + " and " + endDate + ":");
        for (Appointment a : appointments) {
            if (a.getDoctorId().equals(doctorId) &&
                    (a.getAppointmentDate().isEqual(startDate) || a.getAppointmentDate().isAfter(startDate)) &&
                    (a.getAppointmentDate().isEqual(endDate) || a.getAppointmentDate().isBefore(endDate))) {
                a.displayInfo();
                System.out.println("----------------");
            }
        }
    }

    @Override
    public void add(Object entity) {
        if (entity instanceof Appointment) {
            Appointment appointment = (Appointment) entity;
            appointments.add(appointment);
            System.out.println("Appointment added: " + appointment);
        } else {
            System.out.println("Invalid entity type. Must be Appointment.");
        }
    }

    @Override
    public void remove(String id) {
        boolean removed = appointments.removeIf(a -> a.getAppointmentId().equals(id));
        System.out.println(removed ? "Appointment removed: " + id : " Appointment not found: " + id);
    }

    @Override
    public void getAll() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
        } else {
            System.out.println("All Appointments:");
            for (Appointment a : appointments) {
                a.displaySummary();
            }
        }
    }

    @Override
    public void search(String keyword) {
        System.out.println("Searching appointments with keyword: " + keyword);
        for (Appointment a : appointments) {
            if (a.getNotes().contains(keyword) || a.getReason().contains(keyword)) {
                a.displayInfo();
                System.out.println("----------------");
            }
        }
    }

    @Override
    public void searchById(String id) {
        for (Appointment a : appointments) {
            if (a.getAppointmentId().equals(id)) {
                a.displayInfo();
                return;
            }
        }
        System.out.println("Appointment not found: " + id);
    }


}
