import controller.BankingController;
import io.javalin.Javalin;
import routes.Routes;
import server.Server;

public class BankingApp {

    public static void main(String[] args) {
        startApp();
    }

    private static void startApp() {
        Javalin app = Server.configureJavalin();
        Routes routes = new Routes(new BankingController());
        routes.configureRoutes(app);
        // app.stop();
    }

}

