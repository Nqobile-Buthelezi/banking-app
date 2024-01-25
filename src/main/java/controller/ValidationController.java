package controller;

/**
 * The ValidationController class provides methods for validating user inputs,
 * including name validation.
 */
public class ValidationController {

    /**
     * Validates whether the provided name is valid.
     *
     * @param name The name to be validated.
     * @return True if the name is valid (not empty and contains only letters and spaces), false otherwise.
     */
    public boolean isValidName(String name) {
        // Check if the name is not empty
        if (name.isEmpty()) {
            return false;
        }

        // Check if the name contains only letters and spaces
        if (!name.matches("^[a-zA-Z\\s]+$")) {
            return false;
        }

        return true;
    }
}
