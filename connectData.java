import java.sql.*;

public class connectData {
    string url = "recipe_database.sql"


    try (Connection conn = DriverManager.getConnection(url)) {
        // Perform database operations here
        system.out.println("Connected to the database");
    }
    catch (SQLException e) {
        system.out.println("An error occurred. Could not connect to the database.");
        e.printStackTrace();
    }

    public static void main(String[] args) {
        connectData();
    }


}
