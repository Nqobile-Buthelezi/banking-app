package service;

import exceptions.InsufficientFundsException;
import model.Account;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The BankingService class provides business logic related to banking operations,
 * such as creating user tables, creating accounts, depositing, withdrawing, and checking balances.
 * It interacts with the data layer, and in this example, it uses a database for persistence.
 */
public class BankingService {

    /**
     * Creates the user_accounts table in the database if it doesn't exist.
     * This table is used to store user account information.
     */
    public void createUserTable() {
        try (Connection connection = DatabaseConnection.connect()) {
            // Create a table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS user_accounts (\n" +
                    "    id INTEGER PRIMARY KEY,\n" +
                    "    username TEXT UNIQUE,\n" +
                    "    password TEXT,\n" +
                    "    first_name TEXT,\n" +
                    "    last_name TEXT,\n" +
                    "    email TEXT,\n" +
                    "    phone TEXT,\n" +
                    "    address TEXT,\n" +
                    "    city TEXT,\n" +
                    "    postal_code TEXT,\n" +
                    "    date_of_birth DATE,\n" +
                    "    gender TEXT,\n" +
                    "    balance REAL\n" +
                    ");\n";
            try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create the table.");
        }
    }

    /**
     * Creates a new account with the provided customer name and initial balance.
     *
     * @param customerName    The name of the account holder.
     * @param initialBalance  The initial balance of the account.
     * @return                The newly created Account object.
     */
    public Account createAccount(String customerName, double initialBalance) {
        // Implement logic for creating a new account
        // You might interact with the data layer here (e.g., a database)
        // Generate a new Account object and return it
        return new Account(customerName, initialBalance);
    }

    /**
     * Deposits the specified amount into the given account.
     *
     * @param account  The account to deposit into.
     * @param amount   The amount to deposit.
     * @return         The new balance after the deposit.
     */
    public double deposit(Account account, double amount) {
        // Implement logic for depositing into an account
        // Update the account balance and return the new balance
        account.deposit(amount);
        return account.getBalance();
    }

    /**
     * Withdraws the specified amount from the given account.
     *
     * @param account  The account to withdraw from.
     * @param amount   The amount to withdraw.
     * @return         The new balance after the withdrawal.
     * @throws InsufficientFundsException If the account has insufficient funds for the withdrawal.
     */
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

    /**
     * Retrieves the current balance of the given account.
     *
     * @param account  The account to check the balance.
     * @return         The current balance of the account.
     */
    public double getBalance(Account account) {
        // Implement logic for checking account balance
        // Return the current balance
        return account.getBalance();
    }
}
