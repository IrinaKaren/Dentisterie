package model;

import dbaccess.PGSQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Prioriter {
    private int iddents;
    private int idclient;
    private String client;
    private int numero;
    private double cout_traitement;
    private double cout_remplacement;
    private double cout;
    private int id_priorite;
    private int id_situation;
    private String degat;
    private String type;

    // Constructor
    public Prioriter(){}
    
    public Prioriter(int iddents, String client, int numero, double cout_traitement, double cout_remplacement,
                     int id_priorite, int id_situation, String degat, String type) {
        this.iddents = iddents;
        this.client = client;
        this.numero = numero;
        this.cout_traitement = cout_traitement;
        this.cout_remplacement = cout_remplacement;
        this.id_priorite = id_priorite;
        this.id_situation = id_situation;
        this.degat = degat;
        this.type = type;
    }

    // Getter and Setter methods

    public int getIddents() {
        return iddents;
    }

    public void setIddents(int iddents) {
        this.iddents = iddents;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getCout_traitement() {
        return cout_traitement;
    }

    public void setCout_traitement(double cout_traitement) {
        this.cout_traitement = cout_traitement;
    }

    public double getCout_remplacement() {
        return cout_remplacement;
    }

    public void setCout_remplacement(double cout_remplacement) {
        this.cout_remplacement = cout_remplacement;
    }

    public int getId_priorite() {
        return id_priorite;
    }

    public void setId_priorite(int id_priorite) {
        this.id_priorite = id_priorite;
    }

    public int getId_situation() {
        return id_situation;
    }

    public void setId_situation(int id_situation) {
        this.id_situation = id_situation;
    }

    public String getDegat() {
        return degat;
    }

    public void setDegat(String degat) {
        this.degat = degat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }
    
    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }
    
    //function
    public static List<Prioriter> sanitaire(int idclient) throws Exception {
        List<Prioriter> dents = new ArrayList<>();

        try (Connection connection = PGSQLConnection.getConnection()) {
            String sql = "SELECT id_client, client, id_dents, numero, cout_traitement, cout_remplacement, id_priorite, id_situation, degat, type " +
                    "from v_priorite_sanitaire WHERE id_client = ?";   
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idclient);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Prioriter dent = new Prioriter();
                    dent.setIdclient(resultSet.getInt("id_client"));
                    dent.setIddents(resultSet.getInt("id_dents"));
                    dent.setClient(resultSet.getString("client"));
                    dent.setDegat(resultSet.getString("degat"));
                    dent.setType(resultSet.getString("type"));
                    dent.setNumero(resultSet.getInt("numero"));
                    dent.setCout_remplacement(resultSet.getInt("cout_remplacement"));
                    dent.setCout_traitement(resultSet.getInt("cout_traitement"));
                    dent.setId_priorite(resultSet.getInt("id_priorite"));
                    dent.setId_situation(resultSet.getInt("id_situation"));
                    dents.add(dent);
                }
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dents;
    }
    
    public static List<Prioriter> beaute(int idclient) throws Exception {
        List<Prioriter> dents = new ArrayList<>();

        try (Connection connection = PGSQLConnection.getConnection()) {
            String sql = "SELECT id_client, client, id_dents, numero, cout_traitement, cout_remplacement, id_priorite, id_situation, degat, type " +
                    "from v_priorite_beaute WHERE id_client = ?";
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idclient);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Prioriter dent = new Prioriter();
                    dent.setIdclient(resultSet.getInt("id_client"));
                    dent.setIddents(resultSet.getInt("id_dents"));
                    dent.setClient(resultSet.getString("client"));
                    dent.setDegat(resultSet.getString("degat"));
                    dent.setType(resultSet.getString("type"));
                    dent.setNumero(resultSet.getInt("numero"));
                    dent.setCout_remplacement(resultSet.getInt("cout_remplacement"));
                    dent.setCout_traitement(resultSet.getInt("cout_traitement"));
                    dent.setId_priorite(resultSet.getInt("id_priorite"));
                    dent.setId_situation(resultSet.getInt("id_situation"));
                    dents.add(dent);
                }
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dents;
    }
    
    public static List<Prioriter> getPrioriterSanitaire(int idclient) throws Exception {
        List<Prioriter> dents = new ArrayList<>();
        dents.addAll(Prioriter.sanitaire(idclient));
        dents.addAll(Prioriter.beaute(idclient));
        return dents;
    }

    public static List<Prioriter> getPrioriterBeaute(int idclient) throws Exception {
        List<Prioriter> dents = new ArrayList<>();
        dents.addAll(Prioriter.beaute(idclient));
        dents.addAll(Prioriter.sanitaire(idclient));
        return dents;
    }

    public static List<Prioriter> getPriorite(String option, int idclient) throws Exception {
        List<Prioriter> dents = new ArrayList<>();
        if ("sanitaire".equalsIgnoreCase(option)) {
            dents.addAll(Prioriter.getPrioriterSanitaire(idclient));        
        }
        if ("beaute".equalsIgnoreCase(option)) {
            dents.addAll(Prioriter.getPrioriterBeaute(idclient));        
        }
        return dents;
    }
    
//    public static List<Prioriter> getDentsTraiter(double argent, String option, int idclient) throws Exception {
//        List<Prioriter> dents = new ArrayList<>();
//        List<Prioriter> listdents = Prioriter.getPriorite(option, idclient);
//        double cout = 0;
//
//        for (int i = 0; i < listdents.size(); i++) { 
//            if (cout < argent) {
//                if (listdents.get(i).getType().equalsIgnoreCase("traitement")) {
//                    cout += listdents.get(i).getCout_traitement();
//                    dents.add(listdents.get(i));
//                }
//                if (listdents.get(i).getType().equalsIgnoreCase("remplacement")) {
//                    cout += listdents.get(i).getCout_remplacement();
//                    dents.add(listdents.get(i));
//                }
//            }
//        }
//        return dents;
//    }
    
    public static List<Prioriter> getDentsTraiter(double argent, String option, int idclient) throws Exception {
        List<Prioriter> dents = new ArrayList<>();
        List<Prioriter> listdents = Prioriter.getPriorite(option, idclient);
        double cout = 0;

        for (int i = 0; i < listdents.size(); i++) { 
            if(listdents.get(i).getCout_remplacement() <= argent ) {
                    cout = listdents.get(i).getCout_remplacement();
                    listdents.get(i).setType("remplacement");
                    listdents.get(i).setCout(listdents.get(i).getCout_remplacement());
                    dents.add(listdents.get(i));
                    argent -= cout;
            }
            else if(listdents.get(i).getCout_traitement() <= argent ){
                cout = listdents.get(i).getCout_traitement();
                listdents.get(i).setType("traitement");
                listdents.get(i).setCout(listdents.get(i).getCout_traitement());
                dents.add(listdents.get(i));
                argent -= cout;
            }
            else{
                for(int j=i-1 ;j>=0; j--){
                    if(listdents.get(j).getType().equalsIgnoreCase("remplacement")){
                        listdents.get(j).setType("traitement");
                        argent +=  listdents.get(j).getCout() - listdents.get(j).getCout_traitement();
                        listdents.get(j).setCout(listdents.get(j).getCout_traitement());
                        if(argent >= listdents.get(i).getCout_traitement()){
                            break;
                        }
                    }
                }
                if(argent < listdents.get(i).getCout_traitement())throw new Exception("Argent insuffisant");
                i=i-1;
                continue;
            }
        }
        return dents;
    }

}
