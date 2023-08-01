package utility;
import java.sql.*;

public class DatabaseUtil {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mini_proj";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "4239";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
