package server;

import config.AppConfig;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinThymeleaf;
import routes.RoleAccess.Role;

import static routes.RoleAccess.*;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

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
    public static Javalin configureJavalin() 
    {
        // Set up Thymeleaf template engine
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/"); // Path to templates directory
        templateResolver.setSuffix(".html"); // File extension for templates
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCacheable(false); // Disable caching for development

        templateEngine.setTemplateResolver(templateResolver);

        // Integrate Thymeleaf with Javalin
        JavalinThymeleaf.init( templateEngine );

        return Javalin.create( config -> {
            config.staticFiles.add( "/templates", Location.CLASSPATH );

            config.accessManager(( handler, ctx, routeRoles ) -> {
            
                Role userRole = getUserRole( ctx );
                if ( routeRoles.contains( userRole ) ) 
                {
                    handler.handle( ctx );
                } 
                else 
                {
                    
                    ctx.status(401).result("Unauthorised");
                }
            });

        }).start( AppConfig.SERVER_PORT );
    }

}
