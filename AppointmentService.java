package Service;

import Entity.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    private static List<Appointment> appointments = new ArrayList<>();


    public static void addAppointment(Appointment appointment) {
        if (appointment !=null){
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
}
