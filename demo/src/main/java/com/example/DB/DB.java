package com.example.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is about making database for the application.
 * connection is connect the java and postgreSQL.
 * result executing an SQL query.
 * prestatement be able to make SQL statements.
 */
public class DB {
    private Connection connection;
    private ResultSet result; 
    private PreparedStatement prestatement;

    /**
     * DB is the constructor for DB.
     */
    public DB(){
        this.connection = null;
        this.result = null;
        this.prestatement = null;
    }
    /**
     * init is the method that make connection between Java and the postgreSQL.
     * @return true if connection was successfully made.
     * @return false if connection was not successfully made.
     */
    public boolean init(){
        String url = "jdbc:postgresql://localhost:5432/postgres";
		String user = "postgres";
		String password = "pizzaman14";

        try{
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(url, user, password);
            return true;

        } catch (Exception e){
            System.out.println("Connection Failed");
            return false;
        }
    }

    /**
     * insertUserInfo is the method that insert new registered user infomation to the DB.
     * @param id is the username.
     * @param pass is the password.
     */
    public void insertUserInfo(String id, String pass){
        try {
            init();
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO login_table (\"ID\", \"password\")");
            sql.append("VALUES (?,?)");

            prestatement = connection.prepareStatement(sql.toString());
            prestatement.setString(1, id);
            prestatement.setString(2, pass);
            prestatement.executeUpdate();
            System.out.println("successfully stored");
            
        } catch (Exception e) {
            System.out.println("Failed to store");
        }
    }

    /**
     * getPassword is the method that gets the password that is correspond to the username.
     * @param id is the username. 
     * @return password if username is exists in the system, "" if there is error or username is not exist.
     */
    public String getPassword(String id) {
        try{
            init();
            prestatement = connection.prepareStatement("SELECT password FROM login_table WHERE \"ID\" = ?");
            prestatement.setString(1, id);
            result = prestatement.executeQuery();
            if (result.next()) {
                return result.getString("password");
            }
    
        } catch (Exception e) {
            System.out.println("Failed to get password");
        }
        return "";
    }
    /**
     * getID is the method that gets the username based on the username.
     * @param id is the username.
     * @return username if username is exists in the system, "" if there is error or username is not exist.
     */
    public String getID(String id) {
        try{
            init();
            prestatement = connection.prepareStatement("SELECT \"ID\" FROM login_table WHERE \"ID\" = ?");
            prestatement.setString(1, id);
            result = prestatement.executeQuery();
            if (result.next()) {
                return result.getString("ID");
            }
    
        } catch (Exception e) {
            System.out.println("Failed to get ID");
        }
        return "";
    }

    /**
     * getNumberofUsers is the method that gets the total number of users in DB.
     * @return total number of user in DB.
     */
    public int getNumberofUsers(){
        try{
            init();
            prestatement = connection.prepareStatement("SELECT COUNT(*) FROM login_table");
            result = prestatement.executeQuery();
            if(result.next()){
                return result.getInt("count");
            }
        } catch (Exception e) {
            System.out.println("Failed to get number of users");
        }
        return 0;
    }
    


    public void insertNote(String id, String note) {
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO notes (\"ID\", \"note\")");
        sql.append("VALUES (?,?)");
     
        try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
            stmt.setString(1, id);
            stmt.setString(2, note);
            stmt.executeUpdate();
            System.out.println("Successfully inserted note");
        } catch (SQLException e) {
            System.err.println("Failed to insert note: " + e.getMessage());
        }
    }



    public String getNotes(String id) {
        try {
            init();
            prestatement = connection.prepareStatement("SELECT \"note\" FROM notes WHERE \"ID\" =? ORDER BY number DESC LIMIT 1");
            prestatement.setString(1, id);
            result = prestatement.executeQuery();
            if (result.next()) {
                return result.getString("note");
            } else {
                return ""; // No notes found
            }
        } catch (Exception e) {
            System.out.println("Failed to get notes");
            return "";
        }
    }
}