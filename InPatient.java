package Entity;

import java.time.LocalDate;

public class InPatient extends Patient{

    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String roomNumber;
    private String bedNumber;
    private String admittingDoctorId;
    private Double dailyCharges;
}
