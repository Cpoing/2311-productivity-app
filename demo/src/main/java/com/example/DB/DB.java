package com.example.DB;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.Components.ChecklistItem;

import javafx.concurrent.Task;

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
		String password = "taehyun905";

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

    public void insertTask(String username, String description, LocalDate dueDate, LocalTime dueTime, String priority, boolean isChecked, boolean onTime) {
    try {
        init();
        String sql = "INSERT INTO task_table (\"ID\", description, due_date, due_time, priority, is_checked, on_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
        prestatement = connection.prepareStatement(sql);
        prestatement.setString(1, username);
        prestatement.setString(2, description);
        prestatement.setDate(3, Date.valueOf(dueDate)); // Convert LocalDate to SQL Date
        prestatement.setTime(4, Time.valueOf(dueTime)); // Convert LocalTime to SQL Time
        prestatement.setString(5, priority);
        prestatement.setBoolean(6, isChecked);
        prestatement.setBoolean(7, onTime);
        prestatement.executeUpdate();
        System.out.println("Task inserted successfully.");
    } catch (SQLException e) {
        System.out.println("Failed to insert task: " + e.getMessage());
    }
}
    public void updateTask(String username, String description, LocalDate dueDate, LocalTime dueTime, boolean isChecked, boolean onTime) {
    try {
        init();
        String sql = "UPDATE task_table SET is_checked = ?, on_time = ? WHERE \"ID\" = ? AND description = ? AND due_date = ? AND due_time = ?";
        prestatement = connection.prepareStatement(sql);
        prestatement.setBoolean(1, isChecked);
        prestatement.setBoolean(2, onTime);
        prestatement.setString(3, username);
        prestatement.setString(4, description);
        prestatement.setDate(5, Date.valueOf(dueDate));
        prestatement.setTime(6, Time.valueOf(dueTime));

        int rowsUpdated = prestatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Task updated successfully.");
        } else {
            System.out.println("No task found with the given username, description, and due date.");
        }
    } catch (SQLException e) {
        System.out.println("Failed to update task: " + e.getMessage());
    }
}
public List<ChecklistItem> retrieveTasks(String username) {
    List<ChecklistItem> tasks = new ArrayList<>();
    try {
        init();
        String sql = "SELECT description, due_date, due_time, priority, is_checked, on_time FROM task_table WHERE \"ID\" = ?";
        prestatement = connection.prepareStatement(sql);
        prestatement.setString(1, username);
        result = prestatement.executeQuery();
        
        while (result.next()) {
            String description = result.getString("description");
            LocalDate dueDate = result.getDate("due_date").toLocalDate();
            LocalTime dueTime = result.getTime("due_time").toLocalTime();
            String priority = result.getString("priority");
            boolean isChecked = result.getBoolean("is_checked");
            boolean onTime = result.getBoolean("on_time");
            
            // Create a new ChecklistItem object and add it to the list
            ChecklistItem item = new ChecklistItem(description, dueDate, dueTime, priority, isChecked, onTime);
            tasks.add(item);
        }
    } catch (SQLException e) {
        System.out.println("Failed to retrieve tasks: " + e.getMessage());
    }
    return tasks;
}

}