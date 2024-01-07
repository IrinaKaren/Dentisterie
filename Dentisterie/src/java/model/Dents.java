package model;

import dbaccess.PGSQLConnection;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Dents {
    private int id;
    private int numero;
    private int idclient;
    private Timestamp date_rdv;
    private int id_etat;
    private String etat;
    private double cout_nettoyage;
    private double cout_reparation;
    private double cout_enlevement;
    private double cout_remplacement;

    //  constructor
    public Dents() {}
    
    public Dents(Timestamp date_rdv ,int id_client,int numero_dent,int id_etat){
        this.setDate_rdv(date_rdv);
        this.setIdclient(id_client);
        this.setNumero(numero_dent);
        this.setId_etat(id_etat);     
    }
    
    public Dents(int id, int numero, int coutReparation, int coutRemplacement) {
        this.id = id;
        this.numero = numero;
        this.cout_reparation = coutReparation;
        this.cout_remplacement = coutRemplacement;
    }
    
    public Dents(int numero,int idclient){
        this.setNumero(numero);
        this.setIdclient(idclient);
    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for numero
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Getter and Setter for coutReparation
    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }
    
    public Timestamp getDate_rdv() {
        return date_rdv;
    }

    public void setDate_rdv(Timestamp date_rdv) {
        this.date_rdv = date_rdv;
    }

    public int getId_etat() {
        return id_etat;
    }

    public void setId_etat(int id_etat) {
        this.id_etat = id_etat;
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

    public double getCout_remplacement() {
        return cout_remplacement;
    }

    public void setCout_remplacement(double cout_remplacement) {
        this.cout_remplacement = cout_remplacement;
    }
    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    //-------------------------------- DENTS -----------------------------------
    public static List<Dents> getAll() throws Exception{
        List<Dents> listss = new ArrayList<>();
        Connection connection = PGSQLConnection.getConnection();
        String sql = "SELECT id , numero, cout_nettoyage, cout_reparation, cout_remplacement, cout_enlevement FROM dents";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Dents ss = new Dents();
            ss.setId(resultSet.getInt("id"));
            ss.setNumero(resultSet.getInt("numero"));
            ss.setCout_nettoyage(resultSet.getDouble("cout_nettoyage"));
            ss.setCout_nettoyage(resultSet.getDouble("cout_reparation"));
            ss.setCout_nettoyage(resultSet.getDouble("cout_remplacement"));
            ss.setCout_nettoyage(resultSet.getDouble("cout_enlevement"));
            listss.add(ss);
        }
        connection.close();
        return listss;
    }
    
    public static Dents getByNumero(int numero) throws Exception {
        Dents dents = null;
        Connection connection = PGSQLConnection.getConnection();

        try {
            String sql = "SELECT id, numero, cout_nettoyage, cout_reparation, cout_enlevement, cout_remplacement from dents WHERE numero = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, numero);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        dents = new Dents();
                        dents.setId(resultSet.getInt("id"));
                        dents.setNumero(resultSet.getInt("numero"));
                        dents.setCout_nettoyage(resultSet.getDouble("cout_nettoyage"));
                        dents.setCout_reparation(resultSet.getDouble("cout_reparation"));
                        dents.setCout_enlevement(resultSet.getDouble("cout_enlevement"));
                        dents.setCout_remplacement(resultSet.getDouble("cout_remplacement"));
                    }
                }
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return dents;
    }
    
    public static double getCout(String type, int numero) throws Exception {
        Dents dents = Dents.getByNumero(numero);
        double cout = dents.getCout_remplacement();
        if ("Enlevement".equalsIgnoreCase(type)) {
            cout = dents.getCout_enlevement();
        }
        if ("Reparation".equalsIgnoreCase(type)) {
            cout = dents.getCout_reparation();
        }
        if ("Nettoyage".equalsIgnoreCase(type)) {
            cout = dents.getCout_nettoyage();
        }
        return cout;
    }
    
    public static int getIdEtat(String type) {
        if ("Remplacement".equalsIgnoreCase(type)) {
            return 10;
        }
        if ("Enlevement".equalsIgnoreCase(type)) {
            return 0;
        }
        if ("Reparation".equalsIgnoreCase(type)) {
            return 7;
        }
        if ("Nettoyage".equalsIgnoreCase(type)) {
            return 10;
        }
        return -1;
    }

    
    //-------------------- SITUATION DENTAIRE ----------------------------------
    public void situation() throws Exception{
        Connection connection = PGSQLConnection.getConnection();
        String sql = "INSERT INTO situation_dentaire (date_rdv ,id_client, numero_dent, id_etat) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1, this.getDate_rdv());
            preparedStatement.setInt(2, this.getIdclient());
            preparedStatement.setInt(3, this.getNumero());
            preparedStatement.setInt(4, this.getId_etat());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void situationDefault(int id_client) throws Exception{
        List<Dents> dents = getAll();
        Dents dent = new Dents();
        for(int i = 0; i<dents.size(); i++){
            dent.setDate_rdv(Timestamp.valueOf(LocalDateTime.now()));
            dent.setIdclient(id_client);
            dent.setNumero(dents.get(i).getNumero());
            dent.setId_etat(10);
            dent.situation();
        }
    }
    
    public void update() throws Exception {
        Connection connection = PGSQLConnection.getConnection();
        String sql = "UPDATE situation_dentaire " +
                     "SET date_rdv = ?, id_etat = ? " +
                     "WHERE numero_dent = ? AND id_client = ? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1, this.getDate_rdv());
            preparedStatement.setInt(2, this.getId_etat());
            preparedStatement.setInt(3, this.getNumero());
            preparedStatement.setInt(4, this.getIdclient());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Timestamp getDateMax() throws Exception{
        Timestamp date_max = null;
        Connection connection = PGSQLConnection.getConnection();
        String sql = "select max(date_rdv) as date_rdv from situation_dentaire";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            date_max = resultSet.getTimestamp("date_rdv");
        }
        connection.close();
        return date_max;
    }
    
    public static List<Dents> getLastSituation() throws Exception{
        Timestamp date_max = getDateMax();
        List<Dents> dents = new ArrayList();
        Connection connection = PGSQLConnection.getConnection();

        try {
            String sql = "SELECT id, date_rdv, id_client, numero_dent, id_etat from situation_dentaire WHERE date_rdv = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setTimestamp(1, date_max);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Dents dent = new Dents();
                        dent.setId(resultSet.getInt("id"));
                        dent.setDate_rdv(resultSet.getTimestamp("date_rdv"));
                        dent.setIdclient(resultSet.getInt("id_client"));
                        dent.setNumero(resultSet.getInt("numero_dent"));
                        dent.setId_etat(resultSet.getInt("id_etat"));
                        dents.add(dent);
                    }
                }
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return dents;
    }

    public static List<Dents> getSituation(int id_client) throws Exception {
        List<Dents> dents = new ArrayList<>();
        Connection connection = PGSQLConnection.getConnection();

        try {
            String sql = "SELECT id, date_rdv, id_client, numero_dent, id_etat from situation_dentaire WHERE id_client = ?  ORDER BY numero_dent";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id_client);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Dents dent = new Dents();
                        dent.setId(resultSet.getInt("id"));
                        dent.setDate_rdv(resultSet.getTimestamp("date_rdv"));
                        dent.setIdclient(resultSet.getInt("id_client"));
                        dent.setNumero(resultSet.getInt("numero_dent"));
                        dent.setId_etat(resultSet.getInt("id_etat"));

                        // Add the Dents object to the list
                        dents.add(dent);
                    }
                }
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return dents;
    }
    
    public static String[] getOldSituation(int id_client) throws Exception{
        List<Dents> situations = getSituation(id_client);
        String[] old_situations = new String[situations.size()];
        for(int i = 0; i<situations.size(); i++){
            old_situations[i] = situations.get(i).getNumero() + "," + situations.get(i).getId_etat();
        }
        return old_situations;
    }
    
    public static String[] getNewSituation(int id_client, String[] situations) throws Exception {
        String[] old_situations = getOldSituation(id_client);
        int count = 0;
        String[] new_situations;
        for (int i = 0; i < old_situations.length; i++) {
            if (!old_situations[i].equalsIgnoreCase(situations[i])) {
                count++;
            }
        }
        new_situations = new String[count];
        count = 0;
        for (int i = 0; i < old_situations.length; i++) {
            if (!old_situations[i].equalsIgnoreCase(situations[i])) {
                new_situations[count] = situations[i];
                count++;
            }
        }
        return new_situations;
    }
    
    public static void newSituation(int id_client,String[] data) throws Exception{
        for (String pair : data) {
            String[] values = pair.split(",");
            
            if (values.length == 2) {
                try {
                    int numero_dent = Integer.parseInt(values[0]);
                    int id_etat = Integer.parseInt(values[1]);
                    Dents dent = new Dents(Timestamp.valueOf(LocalDateTime.now()),id_client,numero_dent, id_etat);
                    dent.update();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid pair: " + pair);
            }
        }
    }
    
    //----------------------- ETAT ---------------------------------------------
    public static List<Dents> getAllEtat() throws Exception{
        List<Dents> listss = new ArrayList<>();
        Connection connection = PGSQLConnection.getConnection();
        String sql = "SELECT id , nom FROM etat";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Dents ss = new Dents();
            ss.setId(resultSet.getInt("id"));
            ss.setEtat(resultSet.getString("nom"));
            listss.add(ss);
        }
        connection.close();
        return listss;
    }
}
