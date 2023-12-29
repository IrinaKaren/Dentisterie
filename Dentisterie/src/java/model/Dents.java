package model;

import dbaccess.PGSQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Dents {
    private int id;
    private int numero;
    private int idclient;
    private String degat;
    private String type;
    private double coutTraitement;
    private double coutRemplacement;

    // Default constructor
    public Dents() {
    }

    // Constructor with parameters
    public Dents(int id, int numero, int coutTraitement, int coutRemplacement) {
        this.id = id;
        this.numero = numero;
        this.coutTraitement = coutTraitement;
        this.coutRemplacement = coutRemplacement;
    }
    
    public Dents(int numero,int idclient,String degat,String type){
        this.setNumero(numero);
        this.setIdclient(idclient);
        this.setDegat(degat);
        this.setType(type);
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

    // Getter and Setter for coutTraitement
    public double getCoutTraitement() {
        return coutTraitement;
    }

    public void setCoutTraitement(double coutTraitement) {
        this.coutTraitement = coutTraitement;
    }

    // Getter and Setter for coutRemplacement
    public double getCoutRemplacement() {
        return coutRemplacement;
    }

    public void setCoutRemplacement(double coutRemplacement) {
        this.coutRemplacement = coutRemplacement;
    }
    
    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
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
    
    public void situation() throws Exception{
        Connection connection = PGSQLConnection.getConnection();
        String sql = "INSERT INTO situation_dentaire (id_client, numero_dent, degat, type_soin) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, this.getIdclient());
            preparedStatement.setInt(2, this.getNumero());
            preparedStatement.setString(3, this.getDegat());
            preparedStatement.setString(4, this.getType());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Dents getByNumero(int numero) throws Exception {
        Dents dents = null;
        Connection connection = PGSQLConnection.getConnection();

        try {
            String sql = "SELECT id, numero, cout_traitement, cout_remplacement FROM dents WHERE numero = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, numero);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        dents = new Dents();
                        dents.setId(resultSet.getInt("id"));
                        dents.setNumero(resultSet.getInt("numero"));
                        dents.setCoutTraitement(resultSet.getDouble("cout_traitement"));
                        dents.setCoutRemplacement(resultSet.getDouble("cout_remplacement"));
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
        double cout = dents.getCoutRemplacement();
        if ("traitement".equalsIgnoreCase(type)) {
            cout = dents.getCoutTraitement();
        }
        return cout;
    }


}
