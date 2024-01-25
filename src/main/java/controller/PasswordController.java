package controller;

/**
 * The PasswordController class provides methods for validating and assessing the strength
 * of passwords based on various criteria, including length, uppercase and lowercase letters,
 * digits, and the presence of special characters.
 */
public class PasswordController {

    /**
     * Checks if a password contains at least one special character.
     *
     * @param password The password to be validated.
     * @return True if the password contains at least one special character; otherwise, false.
     */
    private boolean passwordIsValid(String password) {
        // Check if the password contains at least one special character
        int count = 0;

        for (int i = 0; i < password.length(); i++) {

            // Checking the character for not being a
            // letter, digit, or space
            if (!Character.isDigit(password.charAt(i))
                    && !Character.isLetter(password.charAt(i))
                    && !Character.isWhitespace(password.charAt(i))) {
                count++;
            }
        }

        return count != 0;
    }

    /**
     * Checks if a password is considered strong based on various criteria.
     *
     * @param password The password to be assessed for strength.
     * @return True if the password is considered strong; otherwise, false.
     */
    public boolean isPasswordStrong(String password) {
        // Check if the password is at least 8 characters long
        if (password.length() < 8) {
            return false;
        }

        // Check if the password contains at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Check if the password contains at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // Check if the password contains at least one digit
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // Check if the password contains at least one special character
        if (!passwordIsValid(password)) {
            return false;
        }

        // If all checks pass, the password is considered strong
        return true;
    }
}
