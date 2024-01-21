package config;

public class AppConfig {
    static String dataBaseDirectory = System.getProperty("user.dir") + "/db";

    // Database configuration
    public static final String DB_URL = "jdbc:sqlite:" + dataBaseDirectory + "/database.db";

    // Server configuration
    public static final int SERVER_PORT = 7000;

    // Other application-wide constants or configurations can be added here
}
