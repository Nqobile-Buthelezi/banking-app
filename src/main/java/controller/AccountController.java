package controller;

import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The AccountController class contains methods related to user account operations,
 * such as inserting user data into the database and checking the uniqueness of usernames.
 */
public class AccountController {

    /**
     * Inserts user data into the user_accounts table in the database.
     *
     * @param username   The username of the user.
     * @param password   The password of the user.
     * @param firstName  The first name of the user.
     * @param lastName   The last name of the user.
     * @return True if the data was successfully inserted; otherwise, false.
     */
    public boolean insertUserData(String username, String password, String firstName, String lastName) {
        try (Connection connection = DatabaseConnection.connect()) {
            String insertSQL = "INSERT INTO user_accounts (username, password, first_name, last_name) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, firstName);
                preparedStatement.setString(4, lastName);

                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the data was successfully inserted
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if a given username is unique in the user_accounts table.
     *
     * @param username The username to check for uniqueness.
     * @return True if the username is unique; otherwise, false.
     * @throws RuntimeException If there is an error checking username uniqueness.
     */
    public boolean isUsernameUnique(String username) {
        try (Connection connection = DatabaseConnection.connect()) {
            // Query to check if the username already exists in the database
            String query = "SELECT COUNT(*) FROM user_accounts WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        // If count is 0, the username is unique; otherwise, it's not
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error checking username uniqueness.");
        }
        return false;
    }
}
