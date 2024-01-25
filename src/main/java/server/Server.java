package server;

import config.AppConfig;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import static routes.RoleAccess.*;

/**
 * The Server class configures and starts the Javalin web server for the application.
 * It sets up static file locations, access management based on user roles, and starts the server.
 */
public class Server {

    /**
     * Configures and starts the Javalin web server.
     *
     * @return The configured Javalin instance.
     */
    public static Javalin configureJavalin() {
        return Javalin.create(config -> {
            // Set static file locations for templates
            config.staticFiles.add("/templates", Location.CLASSPATH);

            // Configure access manager to check user roles for each route
            config.accessManager((handler, ctx, routeRoles) -> {
                // Your access manager logic goes here
                Role userRole = getUserRole(ctx);
                if (routeRoles.contains(userRole)) {
                    handler.handle(ctx);
                } else {
                    // Return 401 Unauthorized for routes with mismatched roles
                    ctx.status(401).result("Unauthorized");
                }
            });
        }).start(AppConfig.SERVER_PORT); // Start the server
    }
}
