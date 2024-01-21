package service;

import exceptions.InsufficientFundsException;
import model.Account;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankingService {

    public void createTable() {
        try (Connection connection = DatabaseConnection.connect()) {
            // Create a table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS accounts (id INTEGER PRIMARY KEY, customer_name TEXT, balance REAL)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create the table.");
        }
    }

    public Account createAccount(String customerName, double initialBalance) {
        // Implement logic for creating a new account
        // You might interact with the data layer here (e.g., a database)
        // Generate a new Account object and return it
        return new Account(customerName, initialBalance);
    }

    public double deposit(Account account, double amount) {
        // Implement logic for depositing into an account
        // Update the account balance and return the new balance
        account.deposit(amount);
        return account.getBalance();
    }

    public double withdraw(Account account, double amount) throws InsufficientFundsException {
        // Implement logic for withdrawing from an account
        // Check for sufficient funds, update the account balance, and return the new balance
        if (account.hasSufficientFunds(amount)) {
            account.withdraw(amount);
            return account.getBalance();
        } else {
            // Handle insufficient funds scenario
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
    }

    public double getBalance(Account account) {
        // Implement logic for checking account balance
        // Return the current balance
        return account.getBalance();
    }
}
