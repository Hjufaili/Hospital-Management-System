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
}
