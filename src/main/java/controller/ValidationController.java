package controller;

import java.time.LocalDate;

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

    public boolean isValidEmail(String email) {
        // Basic email validation using a simple regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public boolean isValidPhone(String phone) {
        // Basic phone number validation (assuming a numeric format)
        return phone.matches("\\d{10}");
    }

    public boolean isValidAddress(String address) {
        return address != null && !address.isEmpty();
    }

    public boolean isValidCity(String city) {
        return city != null && !city.isEmpty();
    }

    public boolean isValidPostalCode(String postalCode) {
        return postalCode != null && postalCode.matches("\\d{4}");
    }

    public boolean isValidDateOfBirth(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        return dateOfBirth != null && dateOfBirth.isBefore(currentDate);
    }

    public boolean isValidGender(String gender) {
        return gender != null && (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("non-binary"));
    }

}
