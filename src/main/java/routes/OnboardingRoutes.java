package routes;

import controller.AccountController;
import controller.PasswordController;
import controller.ValidationController;
import io.javalin.Javalin;

/**
 * The OnboardingRoutes class defines routes related to the user onboarding process.
 * It handles user input validation and account creation during the onboarding phase.
 */
public class OnboardingRoutes {

    private final ValidationController validationController;
    private final PasswordController passwordController;
    private final AccountController accountController;

    /**
     * Constructs an OnboardingRoutes instance with the specified controllers.
     *
     * @param validationController The controller for input validation.
     * @param passwordController   The controller for password-related operations.
     * @param accountController    The controller for account-related operations.
     */
    public OnboardingRoutes(ValidationController validationController, PasswordController passwordController, AccountController accountController) {
        this.validationController = validationController;
        this.passwordController = passwordController;
        this.accountController = accountController;
    }

    /**
     * Configures onboarding-related routes for the specified Javalin app.
     *
     * @param app The Javalin app to configure routes for.
     */
    public void configureOnboardingRoutes(Javalin app) {

        // Handling onboarding form submission
        app.post("/onboarding-part-one", ctx -> {
            // Retrieve form data from the request
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");
            String firstName = ctx.formParam("first-name");
            String lastName = ctx.formParam("last-name");

            // Perform checks
            assert username != null;
            boolean isUsernameUnique = accountController.isUsernameUnique(username);
            assert password != null;
            boolean isPasswordStrong = passwordController.isPasswordStrong(password);
            assert firstName != null;
            boolean isValidFirstName = validationController.isValidName(firstName);
            assert lastName != null;
            boolean isValidLastName = validationController.isValidName(lastName);

            // Check if all conditions are met
            if (isUsernameUnique && isPasswordStrong && isValidFirstName && isValidLastName) {
                // Insert user data into the database (if needed)
                boolean isInserted = accountController.insertUserData(username, password, firstName, lastName);

                // Check if the data is successfully inserted into the database
                if (isInserted) {
                    // Redirect to the next onboarding page
                    ctx.redirect("/onboarding-end");
                } else {
                    // Handle the case where data insertion fails (e.g., display an error message)
                    ctx.result("Failed to create an account. Please try again.");
                }
            } else {
                // Render an error page or redirect to signup page with an error message
                ctx.redirect("/signup?error=true");
            }
        }, RoleAccess.Role.DEFAULT);
    }
}
