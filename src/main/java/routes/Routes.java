package routes;

import controller.BankingController;
import io.javalin.Javalin;

public class Routes {
    private final BankingController bankingController;

    public Routes(BankingController bankingController) {
        this.bankingController = bankingController;
    }

    public void configureRoutes(Javalin app) {
        // Define your routes here
        app.get("/", ctx -> bankingController.renderHtml(ctx, "/templates/index.html"));
        app.post("/createAccount", ctx -> ctx.result(bankingController.createAccount()));
        app.post("/deposit", ctx -> ctx.result(bankingController.deposit()));
        app.post("/withdraw", ctx -> ctx.result(bankingController.withdraw()));
        app.get("/balance", ctx -> ctx.result(bankingController.getBalance()));
    }
}
