package com.example.DB;

import java.sql.*;

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
     * init ius the method that make connection between Java and the postgreSQL.
     */
    public void init(){
        String url = "jdbc:postgresql://localhost:5432/postgres";
		String user = "postgres";
		String password = "taehyun905";

        try{
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");

        } catch (Exception e){
            System.out.println("Connection Failed");
        }
    }

    /**
     * insertUserInfo is the method that insert new registered user infomation to the DB.
     * @param id is the username.
     * @param pass is the password.
     */
    public void insertUserInfo(String id, String pass){
        String url = "jdbc:postgresql://localhost:5432/postgres";
		String user = "postgres";
		String password = "taehyun905";
        
        try {
            StringBuffer sql = new StringBuffer();

            Class.forName("org.postgresql.Driver");
            sql.append("INSERT INTO login_table (\"ID\", \"password\")");
            sql.append("VALUES (?,?)");

            connection = DriverManager.getConnection(url, user, password);
            prestatement = connection.prepareStatement(sql.toString());
            prestatement.setString(1, id);
            prestatement.setString(2, pass);
            prestatement.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Connection Failed");
        }
    }

    /**
     * getPassword is the method that gets the password that is correspond to the username.
     * @param id is the username. 
     * @return password if username is exists in the system, "" if there is error or username is not exist.
     */
    public String getPassword(String id) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "taehyun905";
    
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            prestatement = connection.prepareStatement("SELECT password FROM login_table WHERE \"ID\" = ?");
            prestatement.setString(1, id);
            result = prestatement.executeQuery();
            if (result.next()) {
                return result.getString("password");
            }
    
        } catch (Exception e) {
            System.out.println("Connection Failed");
        }
        return "";
    }
    /**
     * getID is the method that gets the username based on the username.
     * @param id is the username.
     * @return username if username is exists in the system, "" if there is error or username is not exist.
     */
    public String getID(String id) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "taehyun905";
    
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            prestatement = connection.prepareStatement("SELECT \"ID\" FROM login_table WHERE \"ID\" = ?");
            prestatement.setString(1, id);
            result = prestatement.executeQuery();
            if (result.next()) {
                return result.getString("ID");
            }
    
        } catch (Exception e) {
            System.out.println("Connection Failed");
        }
        return "";
    }

    /**
     * getNumberofUsers is the method that gets the total number of users in DB.
     * @return total number of user in DB.
     */
    public int getNumberofUsers(){
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "taehyun905";
    
        try{
            Class.forName("org.postgresql.Driver");
            prestatement = connection.prepareStatement("SELECT COUNT(*) FROM login_table");
            
            connection = DriverManager.getConnection(url, user, password);
            result = prestatement.executeQuery();
            if(result.next()){
                return result.getInt("count");
            }
        } catch (Exception e) {
            System.out.println("Connection Failed");
        }
        return 0;
    }
}