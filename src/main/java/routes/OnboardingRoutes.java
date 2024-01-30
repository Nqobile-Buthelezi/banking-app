package routes;

import controller.AccountController;
import controller.PasswordController;
import controller.ValidationController;
import io.javalin.Javalin;
import io.javalin.http.Context;
import routes.RoleAccess.Role;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

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
        app.post("/onboarding-part-one", this::handleOnboardingFormSubmission, Role.DEFAULT);

        app.post("/onboarding-part-two", this::handleOnboardingEndFormSubmission, Role.DEFAULT);
    }

    private void handleOnboardingEndFormSubmission(Context ctx) {
        // Get the username from the session saved earlier
        String username = ctx.sessionAttribute("username");

        // Retrieve form data from the request (including the new fields)
        String email = ctx.formParam("email");
        String phone = ctx.formParam("phone");
        String address = ctx.formParam("address");
        String city = ctx.formParam("city");
        String postalCode = ctx.formParam("postal-code");
        LocalDate dateOfBirth = LocalDate.parse(ctx.formParam("dob"));
        String gender = ctx.formParam("gender");

        // Perform checks and handle the result
        if (validateSecondFormInput(username, email, phone, address, city, postalCode, dateOfBirth, gender)) {
            boolean isUpdated = updateUserData(username, email, phone, address, city, postalCode, dateOfBirth, gender);

            // Check if the data is successfully updated in the database
            if (isUpdated) {
                // Redirect to a completion page or the user's profile page
                ctx.redirect("/onboarding-complete");
            } else {
                // Handle the case where data update fails (e.g., display an error message)
                ctx.result("Failed to complete onboarding. Please try again.");
            }
        } else {
            // Render an error page or redirect to the onboarding page with an error message
            ctx.redirect("/signup-end?error=true");
        }
    }

    private boolean updateUserData(String username, String email, String phone, String address, String city, String postalCode, LocalDate dateOfBirth, String gender) {
        return accountController.updateUserData(username, email, phone, address, city, postalCode, dateOfBirth, gender);
    }

    private boolean validateSecondFormInput(String username, String email, String phone, String address, String city, String postalCode, LocalDate dateOfBirth, String gender) {
        assert username != null && email != null && phone != null && address != null && city != null && postalCode != null && dateOfBirth != null && gender != null;

        boolean isEmailValid = validationController.isValidEmail(email);
        boolean isPhoneValid = validationController.isValidPhone(phone);
        boolean isAddressValid = validationController.isValidAddress(address);
        boolean isCityValid = validationController.isValidCity(city);
        boolean isPostalCodeValid = validationController.isValidPostalCode(postalCode);
        boolean isDateOfBirthValid = validationController.isValidDateOfBirth(dateOfBirth);
        boolean isGenderValid = validationController.isValidGender(gender);

        return isEmailValid && isPhoneValid && isAddressValid && isCityValid && isPostalCodeValid && isDateOfBirthValid && isGenderValid;
    }


    private void handleOnboardingFormSubmission(Context ctx) throws UnsupportedEncodingException {
        // Retrieve form data from the request
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        String firstName = ctx.formParam("first-name");
        String lastName = ctx.formParam("last-name");

        // Save username
        ctx.sessionAttribute("username", username);

        // Perform checks and handle the result
        boolean isUsernameUnique = accountController.isUsernameUnique(username);
        boolean isPasswordStrong = passwordController.isPasswordStrong(password);
        boolean isValidFirstName = validationController.isValidName(firstName);
        boolean isValidLastName = validationController.isValidName(lastName);

        if (isUsernameUnique && isPasswordStrong && isValidFirstName && isValidLastName) {
            boolean isInserted = insertUserData(username, password, firstName, lastName);

            // Check if the data is successfully inserted into the database
            if (isInserted) {
                // Redirect to the next onboarding page
                ctx.redirect("/signup-end");
            } else {
                // Handle the case where data insertion fails (e.g., display an error message)
                ctx.result("Failed to create an account. Please try again.");
            }
        } else {
            // Construct a meaningful error message based on validation results
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");

            if (!isUsernameUnique) {
                errorMessage.append("Username is not unique. Please choose a different one. ");
            }

            if (!isPasswordStrong) {
                errorMessage.append("Password does not meet the strength criteria. Ensure it has at least 8 characters, including one uppercase letter, one lowercase letter, one special character, and a digit. ");
            }
            // Render an error page or redirect to the signup page with the constructed error message
            String encodedErrorMessage = URLEncoder.encode(String.valueOf(errorMessage), StandardCharsets.UTF_8);
            ctx.redirect("/signup?error=" + encodedErrorMessage);
        }
    }


    private boolean validateUserInput(String username, String password, String firstName, String lastName) {
        assert username != null && password != null && firstName != null && lastName != null;

        boolean isUsernameUnique = accountController.isUsernameUnique(username);
        boolean isPasswordStrong = passwordController.isPasswordStrong(password);
        boolean isValidFirstName = validationController.isValidName(firstName);
        boolean isValidLastName = validationController.isValidName(lastName);

        return isUsernameUnique && isPasswordStrong && isValidFirstName && isValidLastName;
    }

    private boolean insertUserData(String username, String password, String firstName, String lastName) {
        return accountController.insertUserData(username, password, firstName, lastName);
    }



}
