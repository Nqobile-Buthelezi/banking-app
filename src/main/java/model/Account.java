package model;

/**
 * The Account class represents a bank account with a customer name and balance.
 */
public class Account {

    private String customerName;
    private double balance;

    /**
     * Constructs an Account with the specified customer name and initial balance.
     *
     * @param customerName   The name of the account holder.
     * @param initialBalance The initial balance of the account.
     */
    public Account(String customerName, double initialBalance) {
        this.customerName = customerName;
        this.balance = initialBalance;
    }

    /**
     * Gets the current balance of the account.
     *
     * @return The current balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount The amount to be deposited. Must be non-negative.
     * @throws IllegalArgumentException If the deposit amount is negative.
     */
    public void deposit(double amount) {
        // Ensure the deposit amount is non-negative
        if (amount >= 0) {
            balance += amount;
        } else {
            // Handle invalid deposit amount
            throw new IllegalArgumentException("Deposit amount must be non-negative");
        }
    }

    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount The amount to be withdrawn. Must be non-negative.
     * @throws IllegalArgumentException If the withdrawal amount is negative.
     */
    public void withdraw(double amount) {
        // Ensure the withdrawal amount is non-negative
        if (amount >= 0) {
            balance -= amount;
        } else {
            // Handle invalid withdrawal amount
            throw new IllegalArgumentException("Withdrawal amount must be non-negative");
        }
    }

    /**
     * Checks if the account has sufficient funds for a specified amount.
     *
     * @param amount The amount to be checked.
     * @return True if the account has sufficient funds, false otherwise.
     */
    public boolean hasSufficientFunds(double amount) {
        // Check if the account has sufficient funds for a withdrawal
        return balance >= amount;
    }

    // Additional methods or fields can be added based on your requirements
}
