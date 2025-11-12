package Service;

import Entity.Appointment;
import Interface.Appointable;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AppointmentService implements Manageable, Searchable, Appointable {

    private final List<Appointment> appointments;

    public AppointmentService() {
        this.appointments = new ArrayList<>();
    }


    public void addAppointment(Appointment appointment) {
        if (HelperUtils.isNull(appointment) || !HelperUtils.isValidString(appointment.getAppointmentId())) {
            System.err.println("Cannot add null appointment or one with an invalid ID.");
            return;
        }

        for (Appointment existingAppt : appointments) {
            if (existingAppt.getAppointmentId().equals(appointment.getAppointmentId())) {
                System.err.println("Cannot add appointment. ID already exists: " + appointment.getAppointmentId());
                return;
            }
        }

        appointments.add(appointment);
        System.out.println("Appointment added: " + appointment.getAppointmentId());
    }


    public void editAppointment(String appointmentId, Appointment updatedAppointments) {

        if (!HelperUtils.isValidString(appointmentId) || HelperUtils.isNull(updatedAppointments)) {
            System.err.println("Invalid ID or update object provided for editing.");
            return;
        }

        for (int i = 0; i < appointments.size(); i++) {
            if (Objects.equals(appointments.get(i).getAppointmentId(), appointmentId)) {
                updatedAppointments.setAppointmentId(appointmentId);
                appointments.set(i, updatedAppointments);
                System.out.println("Appointment is updated: " + appointmentId);
                return;
            }
        }
        System.out.println("appointmentId not found: " + appointmentId);
    }

    public void removeAppointment(String appointmentsId) {

        if (!HelperUtils.isValidString(appointmentsId)) {
            System.err.println("Invalid appointment ID provided for removal.");
            return;
        }


        boolean removed = appointments.removeIf(m -> m.getAppointmentId().equals(appointmentsId));

        if (removed) {
            System.out.println("Appointment is removed: " + appointmentsId);
        } else {
            System.out.println("Appointment not found: " + appointmentsId);
        }
    }

    public Appointment getAppointmentById(String appointmentsId) {
        if (!HelperUtils.isValidString(appointmentsId)) return null;

        for (Appointment m : appointments) {
            if (m.getAppointmentId() != null && m.getAppointmentId().equals(appointmentsId)) {
                return m;
            }
        }
        System.out.println("Appointment not found: " + appointmentsId);
        return null;
    }

    public void displayAllAppointments() {
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

    public List<Appointment> getAppointmentsByPatient(String patientId) {
        if (!HelperUtils.isValidString(patientId)) return new ArrayList<>();
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

    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        if (!HelperUtils.isValidString(doctorId)) return new ArrayList<>();

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

    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        if (HelperUtils.isNull(date)) return new ArrayList<>();
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

    public void rescheduleAppointment(String appointmentId, LocalDate newDate, LocalTime newTime, String reason) {
        Appointment a = getAppointmentById(appointmentId);
        if (a != null) {
            a.reschedule(newDate, newTime);
            a.setReason(reason);
        } else {
            System.out.println("Appointment not found: " + appointmentId);
        }
    }


    public void rescheduleAppointment(String appointmentId, LocalDate newDate, LocalTime newTime) {
        rescheduleAppointment(appointmentId, newDate, newTime, "Date/time changed.");
    }

    public void rescheduleAppointment(String appointmentId, LocalDate newDate) {
        Appointment a = getAppointmentById(appointmentId);
        if (a != null) {
            rescheduleAppointment(appointmentId, newDate, a.getAppointmentTime(), "Date changed.");
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

//    public Appointment createAppointment(String patientId, String doctorId, LocalDate date) {
//        String id = "APT" + (appointments.size() + 1);
//        Appointment a = new Appointment(id, patientId, doctorId, date, "Not Set", "Scheduled", "", "");
//        addAppointment(a);
//        return a;
//    }
//
//    public void createAppointment(String patientId, String doctorId, LocalDate date, String time) {
//        if (HelperUtils.isValidString(patientId) && HelperUtils.isValidString(doctorId) && HelperUtils.isNotNull(date)) {
//            String id = "APT" + (appointments.size() + 1);
//            Appointment a = new Appointment(id, patientId, doctorId, date, time, "Scheduled", "", "");
//            addAppointment(a);
//        } else {
//            System.err.println("Failed to create appointment: Invalid Patient ID, Doctor ID, or Date.");
//        }
//    }

    public void createAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.add(appointment);
            System.out.println("Appointment created: " + appointment.getAppointmentId());
        } else {
            System.out.println("Cannot create null appointment.");
        }
    }

    public Appointment createAppointment(String patientId, String doctorId, LocalDate date, LocalTime time, String reason) {
        if (!HelperUtils.isValidString(patientId) || !HelperUtils.isValidString(doctorId) || HelperUtils.isNull(date) || HelperUtils.isNull(time)) {
            System.err.println("Failed to create appointment: Invalid Patient ID, Doctor ID, Date, or Time.");
            return null;
        }

        // Use the Lombok builder pattern, relying on field defaults in the Entity for ID, status, and notes.
        Appointment newAppointment = Appointment.builder()
                .patientId(patientId)
                .doctorId(doctorId)
                .appointmentDate(date)
                .appointmentTime(time)
                .reason(reason)
                .build();

        addAppointment(newAppointment); // Delegate to the core add method
        return newAppointment;
    }

    public void displayAppointments(LocalDate date) {
        if(HelperUtils.isFutureDate(date)){
            System.err.println("Invalid appointment: is Future date");
            return;
        }
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

    public void displayAppointments(String doctorId, LocalDate startDate, LocalDate endDate) {
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
            addAppointment((Appointment) entity); // Delegate to the core method
        } else {
            System.err.println("Invalid entity type. Must be Appointment.");
        }
    }

    @Override
    public void remove(String id) {
        removeAppointment(id);
    }

    @Override
    public void getAll() {
        getAllAppointments();
    }

    @Override
    public void search(String keyword) {
        if (!HelperUtils.isValidString(keyword)) return;
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
        Appointment a = getAppointmentById(id);
        if (a != null) {
            System.out.println("--- Appointment Found by ID: " + id + " ---");
            a.displayInfo();
        }
    }




}
