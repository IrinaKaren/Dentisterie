package model;

import dbaccess.PGSQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Operation {
    private int id_client;
    private int numero_dent;

    // Constructor
    public Operation(int id_client, int numero_dent) {
        this.setIdClient(id_client);
        this.setNumeroDent(numero_dent);
    }

    // Getter for id_client
    public int getIdClient() {
        return id_client;
    }

    // Setter for id_client
    public void setIdClient(int id_client) {
        this.id_client = id_client;
    }

    // Getter for numero_dent
    public int getNumeroDent() {
        return numero_dent;
    }

    // Setter for numero_dent
    public void setNumeroDent(int numero_dent) {
        this.numero_dent = numero_dent;
    }
    
    // Insertion
    public void traitement(Connection connection) throws Exception{
        if(connection == null)connection = PGSQLConnection.getConnection();
        String sql = "INSERT INTO traitement (id_client,numero_dent) VALUES (?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, this.getIdClient());
            preparedStatement.setInt(2, this.getNumeroDent());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void remplacement(Connection connection) throws Exception{
        if(connection == null)connection = PGSQLConnection.getConnection();
        String sql = "INSERT INTO remplacement (id_client,numero_dent) VALUES (?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, this.getIdClient());
            preparedStatement.setInt(2, this.getNumeroDent());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void operations(Connection connection,String type,int idclient,int numero) throws Exception{
        try{
            this.setIdClient(idclient);
            this.setNumeroDent(numero);
            if ("remplacement".equalsIgnoreCase(type)) {
                this.remplacement(connection);
            }
            if ("traitement".equalsIgnoreCase(type)) {
                this.traitement(connection);
            }
        }
        catch(Exception ex){
            connection.rollback();
            throw ex;
        }
        finally{
            connection.close();
        }
    }
}
