// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;

// public class DatabaseConnection {
//     private static final String URL = "jdbc:mysql://localhost:3306/RecipeDB";
//     private static final String USER = "root"; // Change to your MySQL username
//     private static final String PASSWORD = ""; // Change to your MySQL password

//     public static Connection getConnection() {
//         try {
//             return DriverManager.getConnection(URL, USER, PASSWORD);
//         } catch (SQLException e) {
//             e.printStackTrace();
//             throw new RuntimeException("Error connecting to the database.");
//         }
//     }
// }

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/RecipeDB"; // Change "RecipeDB" to your database name
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = ""; // Your MySQL password (leave empty if none)

    public static Connection getConnection() {
        try {
            // Load MySQL JDBC Driver (not always required in modern versions)
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            // Establish connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Make sure it's in the same folder.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to database.");
            e.printStackTrace();
        }
        return null;
    }
}