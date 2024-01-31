package routes;

import io.javalin.http.Context;
import io.javalin.security.RouteRole;

/**
 * The RoleAccess class provides functionality to determine the role of a user accessing routes.
 * It defines roles such as DEFAULT, CUSTOMER, EMPLOYEE, MANAGER, and ADMIN, and includes a method
 * to determine the user's role based on the context of the request.
 */
public class RoleAccess {

    /**
     * Gets the role of the user based on the provided Javalin context.
     * The role is determined based on certain conditions, such as query parameters or request headers.
     *
     * @param ctx The Javalin context representing the current request.
     * @return The Role of the user (DEFAULT, CUSTOMER, EMPLOYEE, MANAGER, ADMIN).
     */
    public static Role getUserRole(Context ctx) {
        // Placeholder logic to determine user role
        // This could involve inspecting headers, cookies, or examining the royal insignia

        String onboardingStatus = ctx.sessionAttribute("onboardingStatus");
        if ("completed".equals(onboardingStatus)) {
            return Role.CUSTOMER;
        }

        // For now, let's check if the "admin" query parameter is present
        if (ctx.queryParam("admin") != null) {
            return Role.ADMIN;
        }

        return Role.DEFAULT;
    }

    /**
     * The Role enumeration represents different roles that users can have when accessing routes.
     * Each role corresponds to a RouteRole, allowing for easy role-based access control.
     */
    public enum Role implements RouteRole {
        DEFAULT,        // Default role for all users
        CUSTOMER,       // Regular customer with basic access
        EMPLOYEE,       // Bank employee with additional access
        MANAGER,        // Bank manager with managerial access
        ADMIN;          // System administrator with full access
    }

}
