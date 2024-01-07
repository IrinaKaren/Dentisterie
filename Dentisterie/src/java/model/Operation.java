package model;

import dbaccess.PGSQLConnection;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static model.Dents.getDateMax;

public class Operation {
    private int id;
    private Timestamp date_rdv;
    private int id_client;
    private int numero_dent;
    private int id_etat;
    private String type;
    private double cout;

    // Constructor
    public Operation() {}
    
    public Operation(int id_client, int numero_dent) {
        this.setIdClient(id_client);
        this.setNumeroDent(numero_dent);
    }
    
    public Operation(int id, int id_client, int numero_dent) {
        this.id = id;
        this.id_client = id_client;
        this.numero_dent = numero_dent;
    }

    // Getter Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getIdClient() {
        return id_client;
    }

    public void setIdClient(int id_client) {
        this.id_client = id_client;
    }

    public int getNumeroDent() {
        return numero_dent;
    }

    public void setNumeroDent(int numero_dent) {
        this.numero_dent = numero_dent;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }
    
    // Insertion
    public void insert(Connection connection) throws Exception{
        boolean b = false;
        if(connection == null){
            connection = PGSQLConnection.getConnection();
            b = true;
        }
        String sql = "INSERT INTO operation (date_rdv,id_client,numero_dent,id_etat,type,cout) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1, this.getDate_rdv());
            preparedStatement.setInt(2, this.getIdClient());
            preparedStatement.setInt(3, this.getNumeroDent());
            preparedStatement.setInt(4, this.getId_etat());
            preparedStatement.setString(5, this.getType());
            preparedStatement.setDouble(6, this.getCout());
            preparedStatement.executeUpdate();
            if(b)connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Timestamp getDateMax() throws Exception{
        Timestamp date_max = null;
        Connection connection = PGSQLConnection.getConnection();
        String sql = "select max(date_rdv) as date_rdv from operation";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            date_max = resultSet.getTimestamp("date_rdv");
        }
        connection.close();
        return date_max;
    }
    
    public static List<Operation> getLastOperation(int id_client) throws Exception{
        Timestamp date_max = getDateMax();
        List<Operation> dents = new ArrayList();
        Connection connection = PGSQLConnection.getConnection();

        try {
            String sql = "SELECT id, date_rdv, id_client, numero_dent, id_etat, type, cout from operation WHERE date_rdv = ? and id_client = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setTimestamp(1, date_max);
                preparedStatement.setInt(2, id_client);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Operation dent = new Operation();
                        dent.setId(resultSet.getInt("id"));
                        dent.setDate_rdv(resultSet.getTimestamp("date_rdv"));
                        dent.setIdClient(resultSet.getInt("id_client"));
                        dent.setNumeroDent(resultSet.getInt("numero_dent"));
                        dent.setId_etat(resultSet.getInt("id_etat"));
                        dent.setType(resultSet.getString("type"));
                        dent.setCout(resultSet.getDouble("cout"));
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
    
    //--------------- AU CAS OU MISY TRAITEMENT ETC ----------------------------
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
    
    public static List<Operation> getTraitement(int idclient) throws Exception {
        List<Operation> dents = new ArrayList<>();

        try (Connection connection = PGSQLConnection.getConnection()) {
            String sql = "SELECT id, id_client, numero_dent FROM traitement WHERE id_client = ?";   
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idclient);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Operation dent = new Operation();
                    dent.setId(resultSet.getInt("id"));
                    dent.setIdClient(resultSet.getInt("id_client"));
                    dent.setNumeroDent(resultSet.getInt("numero_dent"));
                    dents.add(dent);
                }
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dents;
    }
    
    public static List<Operation> getRemplacement(int idclient) throws Exception {
        List<Operation> dents = new ArrayList<>();

        try (Connection connection = PGSQLConnection.getConnection()) {
            String sql = "SELECT id, id_client, numero_dent FROM remplacement WHERE id_client = ?";   
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idclient);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Operation dent = new Operation();
                    dent.setId(resultSet.getInt("id"));
                    dent.setIdClient(resultSet.getInt("id_client"));
                    dent.setNumeroDent(resultSet.getInt("numero_dent"));
                    dents.add(dent);
                }
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dents;
    }
}
