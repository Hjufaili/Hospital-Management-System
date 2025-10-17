package Entity;

import java.time.LocalDate;

public class OutPatient extends Patient{
    private Integer visitCount;
    private LocalDate lastVisitDate;
    private String preferredDoctorId;
}
