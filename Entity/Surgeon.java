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
@SuperBuilder // Replaces all complex constructors
@NoArgsConstructor // Replaces the constructor that relies on field defaults
@ToString(callSuper = true)
public class Surgeon extends Doctor implements Displayable {

    private  Integer surgeriesPerformed;
    private  List<String> surgeryTypes;
    private Boolean operationTheatreAccess;

    private static final Double DEFAULT_CONSULTATION_FEE = 250.0;
    private static final Boolean DEFAULT_OT_ACCESS = true;



    public  void performSurgery(String surgeryType){
        if (!operationTheatreAccess) {
            System.out.println("Dr. " + getFirstName() + " does not have Operation Theatre access to perform surgery.");
            return;
        }

        // Safety check for null initialization
        // if (this.surgeriesPerformed == null) this.surgeriesPerformed = 0;
        // if (this.surgeryTypes == null) this.surgeryTypes = new ArrayList<>();

        this.surgeriesPerformed++;
        if (!this.surgeryTypes.contains(surgeryType)) {
            this.surgeryTypes.add(surgeryType);
        }
        System.out.println("Dr. " + getFirstName() + " performed a " + surgeryType + " surgery. " +
                "Total surgeries: " + this.surgeriesPerformed);
    }

    public void updateSurgeryCount(int newCount){
        if (newCount < 0) {
            System.out.println("Surgery count cannot be negative!");
            return;
        }
        this.surgeriesPerformed = newCount;
        System.out.println("Dr. " + getFirstName() +
                "'s surgery count updated to: " + surgeriesPerformed);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Surgeries Performed: " + surgeriesPerformed);
        System.out.println("Surgery Types: " + surgeryTypes);
        System.out.println("Operation Theatre Access: " + (operationTheatreAccess ? "Yes" : "No"));
    }

    @Override
    public void displaySummary() {
        super.displaySummary();
        System.out.println("Type: Surgeon | Surgeries: " + surgeriesPerformed + " | OT Access: " + (operationTheatreAccess ? "Yes" : "No"));
    }
}