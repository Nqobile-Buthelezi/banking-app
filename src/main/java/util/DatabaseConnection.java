package util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import config.AppConfig;

/**
 * The DatabaseConnection class provides methods for establishing a connection to the SQLite database
 * and creating the necessary database file if it doesn't exist.
 */
public class DatabaseConnection {

    /**
     * Establishes a connection to the SQLite database.
     *
     * @return A Connection object representing the database connection.
     * @throws RuntimeException If there is an error connecting to the database.
     */
    public static Connection connect() {
        try {
            // Establish a connection to the SQLite database
            String url = AppConfig.DB_URL;

            // Check if the database file exists, create it if it doesn't
            File databaseFile = new File(url.replace("jdbc:sqlite:", ""));
            if (!databaseFile.exists()) {
                createDatabase(databaseFile);
            }

            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    /**
     * Creates the SQLite database file if it doesn't exist.
     *
     * @param databaseFile The File object representing the database file.
     * @throws RuntimeException If there is an error creating the database file.
     */
    private static void createDatabase(File databaseFile) {
        try {
            if (databaseFile.createNewFile()) {
                System.out.println("Database file created: " + databaseFile.getAbsolutePath());
            } else {
                throw new RuntimeException("Failed to create the database file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create the database file.");
        }
    }
}
