
package edu.cwi;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import static j2html.TagCreator.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class TransactionsController implements CrudHandler {

    private final List<String> transactions = new ArrayList<>();

    @Override
    public void getAll(Context ctx) {

        Map<String, String> accountTransactions = new LinkedHashMap<>();

        for (int i = 0; i < transactions.size(); i++) {
            accountTransactions.put(
                String.valueOf(i),
                transactions.get(i)
            );
        }

        UserAccount account = new UserAccount(
            "00000000-0000-0000-0000-000000000000",
            "Account Holder",
            AppRole.CUSTOMER,
            10.00,
            accountTransactions
        );

        ctx.render(
            "jte/accountPage.jte",
            Map.of(
                "title", "Account",
                "account", account
            )
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
