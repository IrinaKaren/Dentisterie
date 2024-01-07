package model;

import dbaccess.PGSQLConnection;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Prioriter {
    private int id_priorite;
    private Timestamp date_rdv;
    private int id_situation;
    private int iddents;
    private int idclient;
    private String client;
    private int numero;
    private double cout_nettoyage;
    private double cout_reparation;
    private double cout_enlevement;
    private double cout_remplacement;
    private double cout;
    private int id_etat;
    private String etat;

    // Constructor
    public Prioriter(){}
    
    public Prioriter(int iddents, String client, int numero, double cout_reparation, double cout_remplacement,
                     int id_priorite, int id_situation, String degat, String type) {
        this.iddents = iddents;
        this.client = client;
        this.numero = numero;
        this.cout_remplacement = cout_remplacement;
        this.id_priorite = id_priorite;
        this.id_situation = id_situation;
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
    
    public double getCout_nettoyage() {
        return cout_nettoyage;
    }

    public void setCout_nettoyage(double cout_nettoyage) {
        this.cout_nettoyage = cout_nettoyage;
    }

    public double getCout_reparation() {
        return cout_reparation;
    }

    public void setCout_reparation(double cout_reparation) {
        this.cout_reparation = cout_reparation;
    }

    public double getCout_enlevement() {
        return cout_enlevement;
    }

    public void setCout_enlevement(double cout_enlevement) {
        this.cout_enlevement = cout_enlevement;
    }

    public int getId_etat() {
        return id_etat;
    }

    public void setId_etat(int id_etat) {
        this.id_etat = id_etat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    public Timestamp getDate_rdv() {
        return date_rdv;
    }

    public void setDate_rdv(Timestamp date_rdv) {
        this.date_rdv = date_rdv;
    }
    
    //function
    public static List<Prioriter> sanitaire(int idclient) throws Exception {
        List<Prioriter> dents = new ArrayList<>();

        try (Connection connection = PGSQLConnection.getConnection()) {
            String sql = "SELECT id_priorite, id_dents, numero, cout_nettoyage, cout_reparation, cout_enlevement, cout_remplacement, date_rdv, id_situation, id_etat, etat, id_client, client "+
                    "from v_priorite_sanitaire WHERE id_client = ?";   
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idclient);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Prioriter dent = new Prioriter();
                    dent.setId_priorite(resultSet.getInt("id_priorite"));
                    dent.setIddents(resultSet.getInt("id_dents"));
                    dent.setNumero(resultSet.getInt("numero"));
                    dent.setCout_nettoyage(resultSet.getInt("cout_nettoyage"));
                    dent.setCout_reparation(resultSet.getInt("cout_reparation"));
                    dent.setCout_enlevement(resultSet.getInt("cout_enlevement"));
                    dent.setCout_remplacement(resultSet.getInt("cout_remplacement"));
                    dent.setDate_rdv(resultSet.getTimestamp("date_rdv"));
                    dent.setId_situation(resultSet.getInt("id_situation"));
                    dent.setId_etat(resultSet.getInt("id_etat"));
                    dent.setEtat(resultSet.getString("etat"));
                    dent.setIdclient(resultSet.getInt("id_client"));
                    dent.setClient(resultSet.getString("client"));
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
            String sql = "SELECT id_priorite, id_dents, numero, cout_nettoyage, cout_reparation, cout_enlevement, cout_remplacement, date_rdv, id_situation, id_etat, etat, id_client, client " +
                    "from v_priorite_beaute WHERE id_client = ?";
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idclient);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Prioriter dent = new Prioriter();
                    dent.setId_priorite(resultSet.getInt("id_priorite"));
                    dent.setIddents(resultSet.getInt("id_dents"));
                    dent.setNumero(resultSet.getInt("numero"));
                    dent.setCout_nettoyage(resultSet.getInt("cout_nettoyage"));
                    dent.setCout_reparation(resultSet.getInt("cout_reparation"));
                    dent.setCout_enlevement(resultSet.getInt("cout_enlevement"));
                    dent.setCout_remplacement(resultSet.getInt("cout_remplacement"));
                    dent.setDate_rdv(resultSet.getTimestamp("date_rdv"));
                    dent.setId_situation(resultSet.getInt("id_situation"));
                    dent.setId_etat(resultSet.getInt("id_etat"));
                    dent.setEtat(resultSet.getString("etat"));
                    dent.setIdclient(resultSet.getInt("id_client"));
                    dent.setClient(resultSet.getString("client"));
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
        return dents;
    }

    public static List<Prioriter> getPrioriterBeaute(int idclient) throws Exception {
        List<Prioriter> dents = new ArrayList<>();
        dents.addAll(Prioriter.beaute(idclient));
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
    
    public static String getOperationEtat(int id_etat){
        String type = "";
        if(id_etat == 0){
            type = "Remplacement";
        }
        if(id_etat >= 1 && id_etat <= 3){
            type = "Enlevement + Remplacement";
        }
        if(id_etat >= 4 && id_etat <= 6){
            type = "Reparation + Nettoyage";
        }
        if(id_etat >= 7 && id_etat <= 9){
            type = "Nettoyage";
        }
        if(id_etat == 10){
            type = "Parfait";
        }
        return type;
    }
    
    public static double getCoutOperationEtat(int id_etat,int numero) throws Exception{
        Dents dent = Dents.getByNumero(numero);
        double cout = 0;
        if(id_etat == 0){
            cout = dent.getCout_remplacement();
            return cout;
        }
        if(id_etat >= 1 && id_etat <= 3){
            //cout = dent.getCout_enlevement() * (4-id_etat);
            cout = dent.getCout_enlevement() * (id_etat) + dent.getCout_remplacement();
            return cout;
        }
        if(id_etat >= 4 && id_etat <= 6){
            cout = dent.getCout_reparation() * (7-id_etat);
            id_etat = 7;
        }
        if(id_etat >= 7 && id_etat <= 9){
            cout += dent.getCout_nettoyage() * (10-id_etat);
        }
        if(id_etat == 10){
            cout = 0;
        }
        return cout;
    }
    
    public static void etablissementPrioriter(String option,double argent,int idclient) throws Exception{
        List<Prioriter> listprioriter = Prioriter.getPriorite(option, idclient);
        Operation operation = new Operation();
        Connection connection = PGSQLConnection.getConnection();
        double cout_total = 0;
        Timestamp date_now = Timestamp.valueOf(LocalDateTime.now());
        for (int i = 0; i < listprioriter.size() ; i++) {
            double cout = getCoutOperationEtat(listprioriter.get(i).getId_etat(),listprioriter.get(i).getNumero());
            double reste_argent = argent - cout_total;
            if(argent - cout_total < cout)continue;
            cout_total += cout;
            if(cout != 0){ // raha toa ka 10 ilay id_etat
                String type = getOperationEtat(listprioriter.get(i).getId_etat());
                operation.setDate_rdv(date_now);
                operation.setIdClient(listprioriter.get(i).getIdclient());
                operation.setNumeroDent(listprioriter.get(i).getNumero());
                operation.setId_etat(listprioriter.get(i).getId_etat());
                operation.setType(type);
                operation.setCout(cout);
                operation.insert(connection);
                
                Dents d = new Dents(operation.getDate_rdv(), idclient, operation.getNumeroDent(),10);
                d.update();
            }
        }
        connection.close();
    }

}
