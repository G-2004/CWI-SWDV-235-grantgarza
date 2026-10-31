package edu.cwi;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import static j2html.TagCreator.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionsController implements CrudHandler {

    private final List<String> transactions = new ArrayList<>();

    @Override
    public void getAll(Context ctx) {
        ctx.render(
            "jte/transactionsPage.jte",
            java.util.Map.of("transactions", transactions)
        );
    }

    @Override
    public void getOne(Context ctx, String resourceId) {
        ctx.html(
            html(
                body(
                    h1("Transaction"),
                    p("Transaction ID: " + resourceId)
                )
            ).render()
        );
    }

    @Override
    public void create(Context ctx) {
        String transaction = ctx.formParam("transaction");
        transactions.add(transaction);
        ctx.redirect("/transactions");
    }

    @Override
    public void update(Context ctx, String resourceId) {
        String transaction = ctx.formParam("transaction");

        try {
            int id = Integer.parseInt(resourceId);

            if (id >= 0 && id < transactions.size()) {
                transactions.set(id, transaction);
            }

            ctx.redirect("/transactions");
        } catch (NumberFormatException e) {
            ctx.status(400).result("Invalid transaction ID.");
        }
    }

    @Override
    public void delete(Context ctx, String resourceId) {
        try {
            int id = Integer.parseInt(resourceId);

            if (id >= 0 && id < transactions.size()) {
                transactions.remove(id);
            }

            ctx.redirect("/transactions");
        } catch (NumberFormatException e) {
            ctx.status(400).result("Invalid transaction ID.");
        }
    }
}