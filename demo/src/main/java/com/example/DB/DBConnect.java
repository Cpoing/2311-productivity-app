package com.example.DB;

import java.sql.*;

public class DBConnect {

    private Connection connection;
    private Statement statement;
    private ResultSet result; 
    private PreparedStatement prestatement;

    public DBConnect(){
        this.connection = null;
        this.statement = null;
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
}