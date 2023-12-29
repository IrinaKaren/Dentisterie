package main;

import java.util.List;
import model.Prioriter;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Prioriter> listsanitaire = Prioriter.getPrioriterSanitaire(5);
        for(int i = 0; i<listsanitaire.size(); i++){
            System.out.println(" prioriter sanitaire "+listsanitaire.get(i).getClient()+" "+listsanitaire.get(i).getId_priorite()+" "+listsanitaire.get(i).getNumero());
        }
        
        List<Prioriter> listbeaute = Prioriter.getPrioriterBeaute(5);
        for(int i = 0; i<listbeaute.size(); i++){
            System.out.println(" prioriter sanitaire "+listbeaute.get(i).getClient()+" "+listbeaute.get(i).getId_priorite()+" "+listbeaute.get(i).getNumero());
        }
    }
}
