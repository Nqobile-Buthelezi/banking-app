package routes;

import controller.UserController;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;
import routes.RoleAccess.Role;

/**
 * The UserRoutes class configures routes related to user actions in the Javalin web application.
 * It sets up routes for creating accounts, making deposits, withdrawals, and checking account balances.
 * The class uses the UserController to handle user-related route logic.
 */
public class UserRoutes {

    private final UserController userController;

    /**
     * Constructs a new UserRoutes object with the specified UserController.
     *
     * @param userController The UserController used to handle user-related route logic.
     */
    public UserRoutes(UserController userController) {
        this.userController = userController;
    }

    /**
     * Configures routes for user actions in the Javalin web application.
     * Defines routes for creating accounts, making deposits, withdrawals, and checking account balances.
     *
     * @param app The Javalin application to which user-related routes are added.
     */
    public void configureUserRoutes(Javalin app) {
        // Create a model with default role information
        Map<String, Object> userModel = new HashMap<>();
        Role myRole = Role.DEFAULT;
        userModel.put("role", myRole.name());

        // Define routes for user actions
        app.post("/createAccount", ctx -> ctx.result(userController.createAccount()));
        app.post("/deposit", ctx -> ctx.result(userController.deposit()));
        app.post("/withdraw", ctx -> ctx.result(userController.withdraw()));
        app.get("/balance", ctx -> ctx.result(userController.getBalance()));
    }
}
