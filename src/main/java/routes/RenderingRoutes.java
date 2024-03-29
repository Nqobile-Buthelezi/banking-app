package routes;

import controller.RenderingController;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;
import routes.RoleAccess.Role;

/**
 * The RenderingRoutes class configures routes related to rendering templates and HTML content.
 * It utilizes the RenderingController to handle the logic for rendering various pages.
 */
public class RenderingRoutes {

    private final RenderingController renderingController;

    /**
     * Constructs a RenderingRoutes object with the specified RenderingController.
     *
     * @param renderingController The controller responsible for rendering templates and HTML content.
     */
    public RenderingRoutes(RenderingController renderingController) {
        this.renderingController = renderingController;
    }

    /**
     * Configures rendering routes for the Javalin web application.
     * Defines routes for the home page, signup, and login pages.
     *
     * @param app The Javalin application to which rendering routes are added.
     */
    public void configureRenderingRoutes(Javalin app) {
        Map<String, Object> renderingModel = new HashMap<>();
        Role myRole = Role.DEFAULT;
        renderingModel.put("role", myRole.name());

        // Define the home page route
        app.get("/", ctx -> renderingController.renderTemplate(ctx, "/templates/index.html", renderingModel), Role.DEFAULT);

        // Define routes for signup and login
        app.get("/signup", ctx -> {
            // Logic for rendering signup page
            renderingController.renderTemplate(ctx, "templates/onboarding-start.html", renderingModel);
        }, Role.DEFAULT);

        app.get("/signup-end", ctx -> {
            // Logic for rendering the second stage of signing up.
            renderingController.renderTemplate(ctx, "templates/onboarding-end.html", renderingModel);
        }, Role.DEFAULT);

        app.get("/onboarding-complete", ctx -> {
            renderingModel.replace("role", Role.CUSTOMER.name());

            // load userdata into the renderingModel
            renderingModel.put("username", ctx.sessionAttribute("username"));
            renderingModel.put("email", ctx.sessionAttribute("email"));
            renderingModel.put("phone", ctx.sessionAttribute("phone"));
            renderingModel.put("name", ctx.sessionAttribute("name"));

            renderingController.renderTemplate(ctx,"templates/index.html", renderingModel);
        }, Role.CUSTOMER);

        app.get("/login", ctx -> {
            // Logic for rendering login page
            renderingController.renderTemplate(ctx, "templates/login.html", renderingModel);
        }, Role.DEFAULT);
    }
}
