import io.javalin.Javalin;
import routes.Routes;
import server.Server;
import service.BankingService;

public class ApplicationInitialiser {
    /**
     * Initialises the web server, banking services, and configures routes.
     */
    public static void startApp() {
        // Configure Javalin web server
        Javalin app = Server.configureJavalin();

        // Initialise banking services and create necessary database tables
        BankingService bankingService = new BankingService();
        bankingService.createUserTable();

        // Configure routes for the application
        Routes routes = new Routes();
        routes.configureRoutes( app );
    }
}
