package controller;

import io.javalin.http.Context;

public class BankingController {

    public void renderHtml(Context ctx, String templatePath) {
        ctx.render(templatePath);
    }

    public String createAccount() {
        // Implement logic for creating a new account
        return "Account created successfully";
    }

    public String deposit() {
        // Implement logic for depositing into an account
        return "Deposit successful";
    }

    public String withdraw() {
        // Implement logic for withdrawing from an account
        return "Withdrawal successful";
    }

    public String getBalance() {
        // Implement logic for checking account balance
        return "Account balance: $1000"; // Replace with actual balance logic
    }
}
