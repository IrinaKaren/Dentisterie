package main;

import java.util.List;
import model.Dents;
import static model.Dents.getSituation;
import model.Operation;
import model.Prioriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Prioriter.etablissementPrioriter("beaute", 10000, 1);
        Operation.getLastOperation(1);
//          String[] old = Dents.getOldSituation(1);
//          for(int i = 0; i<old.length; i++){
//            System.out.println(old[i]);
//          }
    }
}
