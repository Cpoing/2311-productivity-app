package com.example.DB;

import java.sql.*;

public class DB {

    private Connection connection;
    private ResultSet result; 
    private PreparedStatement prestatement;

    public DB(){
        this.connection = null;
        this.result = null;
        this.prestatement = null;
    }

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