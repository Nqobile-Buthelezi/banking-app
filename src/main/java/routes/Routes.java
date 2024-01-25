package routes;

import controller.*;
import io.javalin.Javalin;

/**
 * The Routes class configures routes for the Javalin web application.
 * It sets up various routes, including those for user actions, onboarding, and rendering templates.
 * The class delegates route handling to specific controllers for each area of functionality.
 */
public class Routes {

    /**
     * Configures routes for the Javalin web application.
     * Defines routes for user actions, onboarding, and rendering templates.
     *
     * @param app The Javalin application to which routes are added.
     */
    public void configureRoutes(Javalin app) {

        // Configure routes for user actions
        UserRoutes userRoutes = new UserRoutes(new UserController());
        userRoutes.configureUserRoutes(app);

        // Configure routes for onboarding processes
        OnboardingRoutes onboardingRoutes = new OnboardingRoutes(
                new ValidationController(),
                new PasswordController(),
                new AccountController()
        );
        onboardingRoutes.configureOnboardingRoutes(app);

        // Configure routes for rendering templates
        RenderingRoutes renderingRoutes = new RenderingRoutes(new RenderingController());
        renderingRoutes.configureRenderingRoutes(app);
    }
}
