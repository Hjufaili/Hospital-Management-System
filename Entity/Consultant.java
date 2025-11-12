package Entity;

import Interface.Displayable;
import Utils.HelperUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@SuperBuilder
@ToString(callSuper = true)
@NoArgsConstructor
public class Consultant extends Doctor implements Displayable {
    private List<String> consultationTypes=DEFAULT_CONSULTATION_TYPES;
    private Boolean onlineConsultationAvailable=DEFAULT_ONLINE_AVAILABLE;
    private Integer consultationDuration=DEFAULT_DURATION;

    // Default values for new fields
    private static final List<String> DEFAULT_CONSULTATION_TYPES = List.of("General", "Review");
    private static final Boolean DEFAULT_ONLINE_AVAILABLE = true;
    private static final Integer DEFAULT_DURATION = 30;

//    public Consultant(List<String> consultationTypes, Boolean onlineConsultationAvailable,
//                      Integer consultationDuration) {
//        this.consultationTypes = consultationTypes;
//        this.onlineConsultationAvailable = onlineConsultationAvailable;
//        this.consultationDuration = consultationDuration;
//    }
//
//
//    public Consultant(String id, String firstName, String lastName, LocalDate dateOfBirth,
//                      String gender, String phoneNumber, String address, String email,
//                      String doctorId, String specialization, String qualification,
//                      Integer experienceYears, String departmentId, Double consultationFee,
//                      List<String> availableSlots, List<Patient> assignedPatients,
//                      List<String> consultationTypes, Boolean onlineConsultationAvailable,
//                      Integer consultationDuration) {
//        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, address, email,
//                doctorId, specialization, qualification, experienceYears, departmentId,
//                consultationFee, availableSlots, assignedPatients);
//        this.consultationTypes = consultationTypes;
//        this.onlineConsultationAvailable = onlineConsultationAvailable;
//        this.consultationDuration = consultationDuration;
//    }
//
//    public Consultant(String doctorId, String specialization, String qualification,
//                      Integer experienceYears, String departmentId, Double consultationFee,
//                      List<String> availableSlots, List<Patient> assignedPatients,
//                      List<String> consultationTypes, Boolean onlineConsultationAvailable,
//                      Integer consultationDuration) {
//        super(doctorId, specialization, qualification, experienceYears, departmentId,
//                consultationFee, availableSlots, assignedPatients);
//        this.consultationTypes = consultationTypes;
//        this.onlineConsultationAvailable = onlineConsultationAvailable;
//        this.consultationDuration = consultationDuration;
//    }
//
//    public Consultant(String firstName, String lastName, String specialization, String departmentId) {
//
//        super(
//                HelperUtils.generateId("PER"), firstName, lastName, (LocalDate) null,
//                "N/A", "N/A", "N/A", "N/A",
//
//                HelperUtils.generateId("DOC"), specialization, "N/A", 0, departmentId,
//                150.0, new ArrayList<>(), new ArrayList<>()
//        );
//
//        this.consultationTypes = DEFAULT_CONSULTATION_TYPES;
//        this.onlineConsultationAvailable = DEFAULT_ONLINE_AVAILABLE;
//        this.consultationDuration = DEFAULT_DURATION;
//    }

    public void scheduleConsultation(String type) {
        if (consultationTypes != null && !consultationTypes.contains(type)) {
            System.out.println(getFirstName() + " does not provide consultations in: " + type);
        } else {
            System.out.println(getFirstName() + " scheduled a " + type +
                    " consultation. Duration: " + consultationDuration + " minutes.");
        }


    }

    public void provideSecondOpinion(String diagnosis) {
        System.out.println("provided a second opinion on diagnosis: " + diagnosis);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Consultation Types: " + consultationTypes);
        System.out.println("Online Consultation Available: " + (onlineConsultationAvailable ? "Yes" : "No"));
        System.out.println("Consultation Duration: " + consultationDuration + " minutes");
    }

    @Override
    public void displaySummary() {
        System.out.println("Consultation Types: " + consultationTypes);
        System.out.println("Online Consultation Available: " + (onlineConsultationAvailable ? "Yes" : "No"));

    }

}