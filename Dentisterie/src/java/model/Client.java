package model;

import dbaccess.PGSQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Client {
    private int id;
    private String nom;

    // Default constructor
    public Client() {
    }

    // Constructor with parameters
    public Client(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    public Client(String nom) {
        this.nom = nom;
    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void insert() throws Exception{
        Connection connection = PGSQLConnection.getConnection();
        String sql = "INSERT INTO client (nom) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, this.getNom());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getIdMax() throws Exception{
        int idclient = 0;
        Connection connection = PGSQLConnection.getConnection();
        String sql = "select max(id) as id from client";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            idclient = resultSet.getInt("id");
        }
        connection.close();
        return idclient;
    }
}
