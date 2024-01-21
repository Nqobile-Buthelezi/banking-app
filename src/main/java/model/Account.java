package model;

public class Account {

    private String customerName;
    private double balance;

    public Account(String customerName, double initialBalance) {
        this.customerName = customerName;
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        // Ensure the deposit amount is non-negative
        if (amount >= 0) {
            balance += amount;
        } else {
            // Handle invalid deposit amount
            throw new IllegalArgumentException("Deposit amount must be non-negative");
        }
    }

    public void withdraw(double amount) {
        // Ensure the withdrawal amount is non-negative
        if (amount >= 0) {
            balance -= amount;
        } else {
            // Handle invalid withdrawal amount
            throw new IllegalArgumentException("Withdrawal amount must be non-negative");
        }
    }

    public boolean hasSufficientFunds(double amount) {
        // Check if the account has sufficient funds for a withdrawal
        return balance >= amount;
    }

    // Additional methods or fields can be added based on your requirements
}
