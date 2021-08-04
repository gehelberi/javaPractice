package by.bsu.practice.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

//соединение с базой данных
public class DBConnection {
    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("resources.database");
        String dbUrl = resource.getString("dbUrl");
        String dbUser = resource.getString("dbUser");
        String dbPass = resource.getString("dbPass");
        return DriverManager.getConnection(dbUrl,dbUser,dbPass);
    }
}
