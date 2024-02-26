import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        String server = "localhost";
        int port = 3306;
        String database = "study_app";
        String username = "root";
        String password = "Eecs2311study";
        
        Connection connection = null;
        
        try {
            // Construct JDBC URL
            String jdbcUrl = "jdbc:mysql://" + server + ":" + port + "/" + database;
            
            // Establish connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            if (connection != null) {
                System.out.println("Connected to the database!");
                // Perform database operations here
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } finally {
            try {
                // Close connection
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}