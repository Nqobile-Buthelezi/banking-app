import io.javalin.Javalin;
import routes.Routes;
import server.Server;
import service.BankingService;

/**
 * The BankingApp class serves as the entry point for the banking application.
 * It initializes the Javalin web server, configures routes, and starts the application.
 * The application includes banking services provided by the BankingService class.
 */
public class BankingApp {

    /**
     * The main method, serving as the entry point for the banking application.
     * Calls the startApp method to initialize and start the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        startApp();
    }

    /**
     * Initializes and starts the banking application.
     * Configures the Javalin web server, creates necessary database tables,
     * configures routes using the Routes class, and starts the web server.
     * Finally, it stops the application gracefully.
     */
    private static void startApp() {
        // Configure Javalin web server
        Javalin app = Server.configureJavalin();

        // Initialize banking services and create necessary database tables
        BankingService bankingService = new BankingService();
        bankingService.createUserTable();

        // Configure routes for the application
        Routes routes = new Routes();
        routes.configureRoutes(app);

        // Uncomment the line below if you want to stop the application gracefully
        // app.stop();
    }
}
