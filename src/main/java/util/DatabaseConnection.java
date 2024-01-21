package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import config.AppConfig;

public class DatabaseConnection {

    public static Connection connect() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the SQLite database
            String url = AppConfig.DB_URL;
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

}
