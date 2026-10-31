package edu.cwi;

import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import static j2html.TagCreator.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TransactionsController implements CrudHandler {

    private final List<String> transactions = new ArrayList<>();

    @Override
    public void getAll(Context ctx) {
        ctx.html(
            html(
                body(
                    h1("Transactions"),

                    form().withMethod("post").withAction("/transactions")
                        .with(
                            input().withType("text").withName("transaction"),
                            button("Add Transaction").withType("submit")
                        ),

                    each(IntStream.range(0, transactions.size()).boxed().toList(), i ->
                        p("ID: " + i + " - " + transactions.get(i))
                    ),

                    h2("Update Transaction"),

                    form().withMethod("post").withAction("/transactions/update")
                        .with(
                            input().withType("number").withName("id"),
                            input().withType("text").withName("transaction"),
                            button("Update").withType("submit")
                        ),

                    h2("Delete Transaction"),

                    form().withMethod("post").withAction("/transactions/delete")
                        .with(
                            input().withType("number").withName("id"),
                            button("Delete").withType("submit")
                        )

                )
            ).render()
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