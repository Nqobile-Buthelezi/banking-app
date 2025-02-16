import io.javalin.Javalin;
import routes.Routes;
import server.Server;
import service.BankingService;

public class ApplicationInitialiser {

    /**
     * Initialises the web server, banking services, and configures routes.
     */
    public static void startApp() {
        Routes routes = new Routes();
        Javalin app = Server.configureJavalin();
        BankingService bankingService = new BankingService();

        bankingService.createUserTable();
        routes.configureRoutes( app );
    }
    
}
