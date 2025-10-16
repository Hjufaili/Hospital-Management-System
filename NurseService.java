package Service;


import Entity.Nurse;

import java.util.ArrayList;
import java.util.List;

public class NurseService {
    private static List<Nurse> nurses = new ArrayList<>();


    public static void addNurse(Nurse nurse) {
        if (nurse !=null){
            nurses.add(nurse);
            System.out.println("Nurse added");
        }else {
            System.out.println("Cannot add null nurse");
        }
    }
}
