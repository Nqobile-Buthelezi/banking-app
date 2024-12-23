package config;

import java.io.File;

/**
 * The AppConfig class holds configuration constants for the application.
 * It includes database-related information and server configuration.
 */
public class AppConfig {

    /**
     * The directory location for the database file.
     * It is set based on the user's working directory.
     */
    static String dataBaseDirectory = System.getProperty("user.dir") + "/src/main/java/db";

    /**
     * The URL for connecting to the SQLite database.
     * It is constructed using the database directory and file name.
     */
    public static final String DB_URL = "jdbc:sqlite:" + dataBaseDirectory + "/database.db";

    /**
     * The port number on which the server will run.
     */
    public static final int SERVER_PORT = 7000;

    // Static initializer block to ensure the database directory exists
    static {
        File dbDir = new File(dataBaseDirectory);
        if (!dbDir.exists()) {
            if (dbDir.mkdirs()) {
                System.out.println("Database directory created: " + dbDir.getAbsolutePath());
            } else {
                System.err.println("Failed to create database directory: " + dbDir.getAbsolutePath());
                throw new RuntimeException("Could not create database directory.");
            }
        }
    }

    /**
     * Private constructor to prevent instantiation of the AppConfig class.
     * All members and methods are static, and the class is meant to be used as a utility class.
     */
    private AppConfig() {
        // Private constructor to prevent instantiation
    }
}
